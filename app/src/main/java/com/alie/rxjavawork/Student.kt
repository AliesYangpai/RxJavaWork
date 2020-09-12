package com.alie.rxjavawork

data class Student(var name: String?, var age: Int, var gender: String?, var cloth: Cloth?,var courses:ArrayList<Course>?) {

    constructor():this(null,0,null,null,null)

    inner class Cloth(var jacket: String?, var pants: String?, var shoes: String?) {
        constructor() : this(null, null, null)
    }

    inner class Course(var name:String?,var time:Int,var difficulty:Int,var teacher: Teacher? ){
        constructor():this(null,0,0,null)
    }

    inner class Teacher(var name:String?,var age:Int,var gender: String?){
        constructor():this(null,0,null)
    }
}