package mbk.io.taskmanager.data.local

import android.content.Context

class Pref(private val context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)


    fun onShowed():Boolean{
        return pref.getBoolean(SHOWED_KEY, false)
    }

    fun onBoardingShow() {
        pref.edit().putBoolean(SHOWED_KEY, true).apply()
    }

    fun saveName(name: String){
        pref.edit().putString(NAME_KEY, name).apply()
    }

    fun getName(): String?{
        return pref.getString(NAME_KEY, "")
    }

    fun savePicture(img: String) {
        pref.edit().putString(IMAGE_KEY, img).apply()
    }

    fun getPicture():String?{
        return pref.getString(IMAGE_KEY, "")
    }

    companion object{
        const val PREF_NAME = "pref.name"
        const val SHOWED_KEY = "showed.key"
        const val NAME_KEY = "name.key"
        const val IMAGE_KEY = "image.key"
    }

}