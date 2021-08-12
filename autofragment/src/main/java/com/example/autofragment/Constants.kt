package com.example.autofragment


object Constants {
    const val packageName = "com.android.newfragment"
    const val className = "AllFragments"

    @JvmStatic
    fun getAllFragments(): List<Pair<String, Class<*>>> {
        val clazz = Class.forName("$packageName.$className")
        val instance = clazz.getDeclaredConstructor().newInstance()
        val filed = clazz.getField("fragments")
        val list = filed.get(instance) as Array<String>

        val result = ArrayList<Pair<String, Class<*>>>()
        for (index in 0 until (list.size) / 2) {
            result.add(Pair(list[index * 2], Class.forName(list[index * 2 + 1])))
        }
        return result
    }

}