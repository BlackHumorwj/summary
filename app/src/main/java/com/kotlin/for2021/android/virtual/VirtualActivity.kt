package com.kotlin.for2021.android.virtual

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kotlin.for2021.CIPHERTEXT_WRAPPER
import com.kotlin.for2021.R
import com.kotlin.for2021.SHARED_PREFS_FILENAME
import com.kotlin.for2021.util.ToastUtil
import dalvik.system.DexClassLoader
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey

/**
 * @author : kingBoy
 * @time 2021/5/28 11:15
 */
@RequiresApi(Build.VERSION_CODES.P)
public class VirtualActivity : AppCompatActivity() {

    private val cryptographyManager = CryptographyManager()

    private val ciphertextWrapper
        get() = cryptographyManager.getCiphertextWrapperFromSharedPrefs(
            applicationContext,
            SHARED_PREFS_FILENAME,
            Context.MODE_PRIVATE,
            CIPHERTEXT_WRAPPER
        )

    companion object {
        private const val ANDROID_KEY_STORE = "AndroidKeyStore"
        private const val DIALOG_FRAGMENT_TAG = "myFragment"
        private const val KEY_NAME_NOT_INVALIDATED = "key_not_invalidated"
        private const val SECRET_MESSAGE = "Very secret message"
        private const val TAG = "MainActivity"
    }

    private lateinit var keyStore: KeyStore
    private lateinit var keyGenerator: KeyGenerator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual)


        init()

        findViewById<TextView>(R.id.tv_name).setOnClickListener {


            //权限申请
            showBiometricPromptPrompt()
        }





    }

    private fun init() {
      //  setupKeyStoreAndKeyGenerator()
       // val (defaultCipher: Cipher, cipherNotInvalidated:  Cipher) = setupCiphers()
    }





    @RequiresApi(Build.VERSION_CODES.P)
    private fun showBiometricPromptPrompt() {

        ToastUtil.customToast2(applicationContext, "你好")


        ciphertextWrapper?.let {
            val biometricPrompt = BiometricPrompt.Builder(this)
                .setTitle("你好")
                .setSubtitle("subTitle")
                .setNegativeButton(
                    "取消",
                    ContextCompat.getMainExecutor(this),
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            ToastUtil.customToast2(applicationContext, "取消按钮")
                        }
                    })
                .setDescription("描述信息").build()


            val secretKeyName = getString(R.string.secret_key_name)
            val cipher = cryptographyManager.getInitializedCipherForDecryption(secretKeyName,it.initializationVector)


            biometricPrompt.authenticate(
                BiometricPrompt.CryptoObject(cipher),
                CancellationSignal(),
                ContextCompat.getMainExecutor(this),
                object :
                    BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                        super.onAuthenticationError(errorCode, errString)

                        ToastUtil.customToast2(applicationContext, "${errorCode} --${errString}")

                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()

                        ToastUtil.customToast2(applicationContext, "onAuthenticationFailed")

                    }

                    override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                        super.onAuthenticationHelp(helpCode, helpString)
                        ToastUtil.customToast2(applicationContext, "${helpCode} --${helpString}")


                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                        super.onAuthenticationSucceeded(result)


                    }


                })
        }

    }

    /**
     * Initialize the [Cipher] instance with the created key in the [createKey] method.
     *
     * @param keyName the key name to init the cipher
     * @return `true` if initialization succeeded, `false` if the lock screen has been disabled or
     * reset after key generation, or if a fingerprint was enrolled after key generation.
     */
    private fun initCipher(cipher: Cipher, keyName: String): Boolean {
        try {
            keyStore.load(null)
            cipher.init(Cipher.ENCRYPT_MODE, keyStore.getKey(keyName, null) as SecretKey)
            return true
        } catch (e: Exception) {
            when (e) {
                is KeyPermanentlyInvalidatedException -> return false
                is KeyStoreException,
                is CertificateException,
                is UnrecoverableKeyException,
                is IOException,
                is NoSuchAlgorithmException,
                is InvalidKeyException -> throw RuntimeException("Failed to init Cipher", e)
                else -> throw e
            }
        }
    }

    /**
     * Sets up KeyStore and KeyGenerator
     */
    private fun setupKeyStoreAndKeyGenerator() {
        try {
            keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        } catch (e: KeyStoreException) {
            throw RuntimeException("Failed to get an instance of KeyStore", e)
        }

        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE)
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is NoSuchProviderException ->
                    throw RuntimeException("Failed to get an instance of KeyGenerator", e)
                else -> throw e
            }
        }
    }


    /**
     * Sets up default cipher and a non-invalidated cipher
     */
    private fun setupCiphers(): Pair<Cipher, Cipher> {
        val defaultCipher: Cipher
        val cipherNotInvalidated: Cipher
        try {
            val cipherString = "${KeyProperties.KEY_ALGORITHM_AES}/${KeyProperties.BLOCK_MODE_CBC}/${KeyProperties.ENCRYPTION_PADDING_PKCS7}"
            defaultCipher = Cipher.getInstance(cipherString)
            cipherNotInvalidated = Cipher.getInstance(cipherString)
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is NoSuchPaddingException ->
                    throw RuntimeException("Failed to get an instance of Cipher", e)
                else -> throw e
            }
        }
        return Pair(defaultCipher, cipherNotInvalidated)
    }

}