package com.example.jetpack.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class PreferenceHelper(context: Context) {
	private object PrefKeys {

		const val IS_FIRST_APP_STARTUP = "is_first_app_startup"

		const val PLATFORMID = "PLATFORMID"
		const val NETKEY = "NETKEY"
		const val NETKEY_CREATED_AT = "NETKEY_CREATED_AT"
		const val IS_NETKEY_EXPIRED = "IS_NETKEY_EXPIRED"

		const val IS_LOGGED_IN = "IS_LOGGED_IN"
		const val MOBILENO = "MOBILENO"
		const val TOKEN = "TOKEN"
		const val PASS = "PASS"
		const val SALT = "SALT"
		const val IS_OTP_SENT = "IS_OTP_SENT"
		const val OTP_SENT_AT = "OTP_SENT_AT"
		const val IS_OTP_VERIFIED = "IS_OTP_VERIFIED"
		const val IS_USER_NEW = "IS_USER_NEW"
		const val IS_AUTH = "IS_AUTH"
		const val IS_SHOWWELCOME = "IS_SHOWWELCOME"
		const val IS_MASTERKEY_CREATED = "IS_MASTERKEY_CREATED"


		const val IS_BLE_CONNECTED = "IS_BLE_CONNECTED"
		const val CONNECTED_BLE_DEVICE = "CONNECTED_BLE_DEVICE"
		const val IS_USR_HAS_BLE_DEVICE = "IS_USR_HAS_BLE_DEVICE"
		const val REGISTERED_BLE_DEVICE = "REGISTERED_BLE_DEVICE"
		const val IS_FINGERS_ENROLLED = "IS_FINGERS_ENROLLED"
		const val FINGERS_ENROLLED = "FINGERS_ENROLLED"
		const val IS_REGISTERED_BLE_DEVICE_CONNECTED = "IS_REGISTERED_BLE_DEVICE_CONNECTED"


	}

	private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

	val sharedPreferences: SharedPreferences =EncryptedSharedPreferences.create(
		// passing a file name to share a preferences
		"jetpack_usrprefs",
		masterKeyAlias,
		context,
		EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
		EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
	)

	var isFirstAppStartup: Boolean
		get() = sharedPreferences.getBoolean(PrefKeys.IS_FIRST_APP_STARTUP, true)
		set(value) = sharedPreferences.edit().putBoolean(PrefKeys.IS_FIRST_APP_STARTUP, value).apply()

	var plaformId: String
		get() = sharedPreferences.getString(PrefKeys.PLATFORMID, "") ?: ""
		set(value) = sharedPreferences.edit().putString(PrefKeys.PLATFORMID, value).apply()

	var netkey: String?
		get() = sharedPreferences.getString(PrefKeys.NETKEY, null)
		set(value) = sharedPreferences.edit().putString(PrefKeys.NETKEY, value).apply()

	var netkeyCreatedAt: String?
		get() = sharedPreferences.getString(PrefKeys.NETKEY_CREATED_AT, null)
		set(value) = sharedPreferences.edit().putString(PrefKeys.NETKEY_CREATED_AT, value).apply()

	var isNetkeyExpired: Boolean
		get() = sharedPreferences.getBoolean(PrefKeys.IS_NETKEY_EXPIRED, false)
		set(value) = sharedPreferences.edit().putBoolean(PrefKeys.IS_NETKEY_EXPIRED, value).apply()

	var mobileno: String
		get() = sharedPreferences.getString(PrefKeys.MOBILENO, "") ?: ""
		set(value) = sharedPreferences.edit().putString(PrefKeys.MOBILENO, value).apply()

	var isLoggedin: Boolean
		get() = sharedPreferences.getBoolean(PrefKeys.IS_LOGGED_IN, false)
		set(value) = sharedPreferences.edit().putBoolean(PrefKeys.IS_LOGGED_IN, value).apply()

	var token: String
		get() = sharedPreferences.getString(PrefKeys.TOKEN, "") ?: ""
		set(value) = sharedPreferences.edit().putString(PrefKeys.TOKEN, value).apply()

	var pass: String
		get() = sharedPreferences.getString(PrefKeys.PASS, "") ?: ""
		set(value) = sharedPreferences.edit().putString(PrefKeys.PASS, value).apply()

	var salt: String
		get() = sharedPreferences.getString(PrefKeys.SALT, "") ?: ""
		set(value) = sharedPreferences.edit().putString(PrefKeys.SALT, value).apply()

	var isOtpSent: Long
		get() = sharedPreferences.getLong(PrefKeys.IS_OTP_SENT, 0)
		set(value) = sharedPreferences.edit().putLong(PrefKeys.IS_OTP_SENT, value).apply()

	var otpSentAt: String
		get() = sharedPreferences.getString(PrefKeys.OTP_SENT_AT, "") ?: ""
		set(value) = sharedPreferences.edit().putString(PrefKeys.OTP_SENT_AT, value).apply()

	var isOtpVerified: Boolean
		get() = sharedPreferences.getBoolean(PrefKeys.IS_OTP_VERIFIED, false)
		set(value) = sharedPreferences.edit().putBoolean(PrefKeys.IS_OTP_VERIFIED, value).apply()

	var isUserNew: Boolean
		get() = sharedPreferences.getBoolean(PrefKeys.IS_USER_NEW, false)
		set(value) = sharedPreferences.edit().putBoolean(PrefKeys.IS_USER_NEW, value).apply()

	var isAuth: Boolean
		get() = sharedPreferences.getBoolean(PrefKeys.IS_AUTH, false)
		set(value) = sharedPreferences.edit().putBoolean(PrefKeys.IS_AUTH, value).apply()

	var isShowWelcome: Boolean
		get() = sharedPreferences.getBoolean(PrefKeys.IS_SHOWWELCOME, false)
		set(value) = sharedPreferences.edit().putBoolean(PrefKeys.IS_SHOWWELCOME, value).apply()

	var isMasterKeyCreated: Boolean
		get() = sharedPreferences.getBoolean(PrefKeys.IS_MASTERKEY_CREATED, false)
		set(value) = sharedPreferences.edit().putBoolean(PrefKeys.IS_MASTERKEY_CREATED, value).apply()

	var isBleConnected: Boolean
		get() = sharedPreferences.getBoolean(PrefKeys.IS_BLE_CONNECTED, false)
		set(value) = sharedPreferences.edit().putBoolean(PrefKeys.IS_BLE_CONNECTED, value).apply()

	var bleDevice: String?
		get() = sharedPreferences.getString(PrefKeys.CONNECTED_BLE_DEVICE, null)
		set(value) = sharedPreferences.edit().putString(PrefKeys.CONNECTED_BLE_DEVICE, value).apply()

	var isUsrHasBleDevice: Boolean
		get() = sharedPreferences.getBoolean(PrefKeys.IS_USR_HAS_BLE_DEVICE, false)
		set(value) = sharedPreferences.edit().putBoolean(PrefKeys.IS_USR_HAS_BLE_DEVICE, value).apply()

	var registeredBleDevice: String?
		get() = sharedPreferences.getString(PrefKeys.REGISTERED_BLE_DEVICE, null)
		set(value) = sharedPreferences.edit().putString(PrefKeys.REGISTERED_BLE_DEVICE, value).apply()

	var isFingersEnrolled: Boolean
		get() = sharedPreferences.getBoolean(PrefKeys.IS_FINGERS_ENROLLED, false)
		set(value) = sharedPreferences.edit().putBoolean(PrefKeys.IS_FINGERS_ENROLLED, value).apply()

	var isRegisteredBleDeviceConnected: Boolean
		get() = sharedPreferences.getBoolean(PrefKeys.IS_REGISTERED_BLE_DEVICE_CONNECTED, false)
		set(value) = sharedPreferences.edit().putBoolean(PrefKeys.IS_REGISTERED_BLE_DEVICE_CONNECTED, value).apply()

	var fingersEnrolled: String?
		get() = sharedPreferences.getString(PrefKeys.FINGERS_ENROLLED, null)
		set(value) = sharedPreferences.edit().putString(PrefKeys.FINGERS_ENROLLED, value).apply()


	fun clearTimeCalculation() {
		sharedPreferences.edit().remove(PrefKeys.IS_OTP_SENT).apply()
	}

	fun clear() {
		sharedPreferences.edit().clear().apply()
	}

	private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
		val editor = this.edit()
		operation(editor)
		editor.apply()
	}

	private operator fun SharedPreferences.set(key: String, value: Any?) {
		when (value) {
			is String? -> edit { it.putString(key, value) }
			is Int -> edit { it.putInt(key, value) }
			is Boolean -> edit { it.putBoolean(key, value) }
			is Float -> edit { it.putFloat(key, value) }
			is Long -> edit { it.putLong(key, value) }
			else -> throw UnsupportedOperationException("Not yet implemented")
		}
	}

	private inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
		return when (T::class) {
			String::class -> getString(key, defaultValue as? String) as T?
			Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
			Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
			Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
			Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
			else -> null
		}
	}
}