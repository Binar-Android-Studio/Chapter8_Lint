@file:Suppress("DEPRECATION")

package com.example.challangechapter6.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.challangechapter6.KEY_IMAGE_URI
import com.example.challangechapter6.blur.BlurViewModel
import com.example.challangechapter6.databinding.ActivityProfileBinding
import timber.log.Timber

@Suppress("PrivatePropertyName")
class ProfileActivity : AppCompatActivity() {
    private val REQUEST_CODE_IMAGE = 100
    private val REQUEST_CODE_PERMISSIONS = 101

    private val KEY_PERMISSIONS_REQUEST_COUNT = "KEY_PERMISSIONS_REQUEST_COUNT"
    private val MAX_NUMBER_REQUEST_PERMISSIONS = 2

    private val permissions = listOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private var permissionRequestCount: Int = 0
    private lateinit var binding : ActivityProfileBinding

    private lateinit var viewModel: BlurViewModel
    private lateinit var  sharedPrefs : SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.let {
            permissionRequestCount = it.getInt(KEY_PERMISSIONS_REQUEST_COUNT, 0)
        }

        // Make sure the app has correct permissions to run
        requestPermissionsIfNecessary()
        // Get the ViewModel

        viewModel = ViewModelProviders.of(this)[BlurViewModel::class.java]
        val imageUriExtra = intent.getStringExtra(KEY_IMAGE_URI)
        if(imageUriExtra != "DEFAULT_KEY_IMAGE_URI"){
            viewModel.setImageUri(imageUriExtra)
            viewModel.imageUri?.let { imageUri ->
                Glide.with(this).load(imageUri).into(binding.imageProfle)
            }
        }

//       Set Data
        sharedPrefs = getSharedPreferences("registerData", Context.MODE_PRIVATE)
        val nameData = sharedPrefs.getString("name", null)
        val username = sharedPrefs.getString("username", null)
        val email = sharedPrefs.getString("email", null)
        val phone = sharedPrefs.getString("phone", null)
        val aboutme = sharedPrefs.getString("aboutme", null)
        val address = sharedPrefs.getString("address", null)

        binding.nama.setText(nameData)
        binding.username.setText(username)
        binding.phonenumber.setText(phone)
        binding.alamat .setText(address)
        binding.email.setText(email)
        binding.aboutMe.text = aboutme


        binding.logout.setOnClickListener {
            sharedPrefs = getSharedPreferences("registerData", Context.MODE_PRIVATE)
            val addData = sharedPrefs.edit()
            addData.putString("passwordlgn", null)
            addData.putString("usernamelgn", null)
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.editProfile.setOnClickListener{
            profileUpdate()
        }

        binding.updateButton.setOnClickListener{
            viewModel.applyBlur(2)
        }
    }

    private fun profileUpdate(){
        val chooseIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(chooseIntent, REQUEST_CODE_IMAGE)
    }

    private fun requestPermissionsIfNecessary() {
        if (!checkAllPermissions()) {
            if (permissionRequestCount < MAX_NUMBER_REQUEST_PERMISSIONS) {
                permissionRequestCount += 1
                ActivityCompat.requestPermissions(
                    this,
                    permissions.toTypedArray(),
                    REQUEST_CODE_PERMISSIONS
                )
            } else {
                Toast.makeText(
                    this,
                    "Go to Settings -> Apps and Notifications ->\n" +
                            "        WorkManager Demo -> App Permissions and grant access to Storage.",
                    Toast.LENGTH_LONG
                ).show()
                binding.editProfile.isEnabled = false
            }
        }
    }

    /** Permission Checking  */
    private fun checkAllPermissions(): Boolean {
        var hasPermissions = true
        for (permission in permissions) {
            hasPermissions = hasPermissions and isPermissionGranted(permission)
        }
        return hasPermissions
    }

    private fun isPermissionGranted(permission: String) =
        ContextCompat.checkSelfPermission(this, permission) ==
                PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            requestPermissionsIfNecessary() // no-op if permissions are granted already.
        }
    }

    /** Image Selection  */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_IMAGE -> data?.let { handleImageRequestResult(data) }
                else -> Timber.d("Unknown request code.")
            }
        } else {
            Timber.e(String.format("Unexpected Result code %s", resultCode))
        }
    }

    private fun handleImageRequestResult(intent: Intent) {
        // If clipdata is available, we use it, otherwise we use data
        val imageUri: Uri? = intent.clipData?.getItemAt(0)?.uri ?: intent.data

        if (imageUri == null) {
            Timber.e("Invalid input image Uri.")
            return
        }

        val filterIntent = Intent(this, ProfileActivity::class.java)
        filterIntent.putExtra(KEY_IMAGE_URI, imageUri.toString())
        startActivity(filterIntent)
    }
}