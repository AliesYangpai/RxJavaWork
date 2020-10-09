package com.alie.rxjavawork

object Util {
    fun getAllStudents(): ArrayList<Student> {
        var list = ArrayList<Student>()
        val student1 = Student("zhao", 18, "女",
            Student().Cloth("七匹狼", "运动裤", "aj"), getAllCurseA())
        val student2 =
            Student("qian", 16, "男",
                Student().Cloth("羽绒服", "牛仔裤", "lining"), getAllCurseB())
        val student3 = Student("sun", 17, "女",
            Student().Cloth("背心", "裙子", "anta"), getAllCurseC())
        val student4 = Student("li", 12, "女",
            Student().Cloth("校服", "校裤", "拖鞋"), getAllCurseD())
        list.add(student1)
        list.add(student2)
        list.add(student3)
        list.add(student4)
        return list
    }

    fun getAllCurseA(): ArrayList<Student.Course> {
        val list = ArrayList<Student.Course>()
        var course1 = Student().Course("语文", 20, 1, Student().Teacher("Tom", 26, "男"))
        var course2 = Student().Course("数学", 20, 4, Student().Teacher("Jerry", 26, "男"))
        var course3 = Student().Course("英语", 20, 4, Student().Teacher("Lisa", 30, "女"))
        var course4 = Student().Course("物理", 20, 4, Student().Teacher("Bob", 32, "男"))
        list.add(course1)
        list.add(course2)
        list.add(course3)
        list.add(course4)
        return list
    }

    fun getAllCurseB(): ArrayList<Student.Course> {
        val list = ArrayList<Student.Course>()
        var course2 = Student().Course("数学", 20, 4, Student().Teacher("Jerry", 26, "男"))
        var course3 = Student().Course("英语", 20, 4, Student().Teacher("Lisa", 30, "女"))
        var course4 = Student().Course("物理", 20, 4, Student().Teacher("Bob", 32, "男"))
        list.add(course2)
        list.add(course3)
        list.add(course4)
        return list
    }

    fun getAllCurseC(): ArrayList<Student.Course> {
        val list = ArrayList<Student.Course>()
        var course2 = Student().Course("化学", 20, 4, Student().Teacher("Jerry", 26, "男"))
        var course3 = Student().Course("体育", 20, 4, Student().Teacher("Lisa", 30, "女"))
        var course4 = Student().Course("生物", 20, 4, Student().Teacher("Bob", 32, "男"))
        list.add(course2)
        list.add(course3)
        list.add(course4)
        return list
    }

    fun getAllCurseD(): ArrayList<Student.Course> {
        val list = ArrayList<Student.Course>()
        var course2 = Student().Course("美术", 20, 4, Student().Teacher("Jerry", 26, "男"))
        var course3 = Student().Course("历史", 20, 4, Student().Teacher("Lisa", 30, "女"))
        var course4 = Student().Course("政治", 20, 4, Student().Teacher("Bob", 32, "男"))
        list.add(course2)
        list.add(course3)
        list.add(course4)
        return list
    }

    fun getAllNumbersInt():ArrayList<Int> {
        val list = ArrayList<Int>()
        list.add(1)
        list.add(2)
        list.add(3)
        list.add(4)
        return list
    }

    fun getAllNumberString():ArrayList<String> {
        val list = ArrayList<String>()
        list.add("A")
        list.add("B")
        list.add("C")
        return list
    }
}