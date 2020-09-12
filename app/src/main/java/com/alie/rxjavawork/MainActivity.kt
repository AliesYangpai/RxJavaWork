package com.alie.rxjavawork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.operators.observable.ObservableSubscribeOn
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doOperate()
    }


    private fun doOperate() {
//        rxjava01()
//        rxjava02()
//        rxjava03()
//        rxjava04()
//        rxjava05()
//        rxjava06()
          rxjava07()
    }

    /**
     * 01初步使用，发射一下事件
     */
    private fun rxjava01() {
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext("你好")
                emitter.onNext("My friend")
            }
        }).subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                Log.i(TAG, "onNext:$t")
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        })
    }

    /**
     * 02输出所有学生的姓名
     * map使用
     */
    fun rxjava02() {

        // 写法1：
        Observable.fromIterable(Util.getAllStudents())
            .map { student -> student.name }
            .subscribe { name -> Log.i(TAG, "===rxjava02=name:$name") }


        // 写法2：(他娘的，map变换在kotlin版本中这么简单，直接就it.name ~~~)
        val subscribe =
            Observable.fromIterable(Util.getAllStudents())
                .map { it.name }
                .subscribe { Log.i(TAG, "===rxjava02=name:$it") }
    }

    /**
     * 输出所有学生的鞋的名称
     * 03(实在想不到例子了）
     * map map
     */
    fun rxjava03() {
        Observable.fromIterable(Util.getAllStudents())
            .map { it.cloth }
            .map { it.shoes }
            .subscribe {
                Log.i(TAG, "====rxjava03,shoes:$it")
            }
    }

    /**
     * 输出所有性别是女的学生的鞋~（只能想到这种业务了）
     * map map filter
     */
    fun rxjava04() {
        Observable.fromIterable(Util.getAllStudents())
            .filter { it.gender.equals("女") }
            .map { it.cloth }
            .map { it.shoes }
            .subscribe { Log.i(TAG, "女生的鞋：$it") }
    }

    /**
     * 打印所有学生所修的课程名称
     * flatMap(flatMapIterable) + map
     */
    fun rxjava05() {
        // 写法1：在flatMap中返回Observable.fromIterable(it.courses)

        Observable.fromIterable(Util.getAllStudents())
            .flatMap { Observable.fromIterable(it.courses) }
            .map { it.name }
            .subscribe { Log.i(TAG, "rxjava05:allcourse:$it") }
        // 写法2：直接使用哪个
        Observable.fromIterable(Util.getAllStudents())
            .flatMapIterable { it.courses }
            .map { it.name }
            .subscribe { Log.i(TAG, "rxjava05:allcourse:$it") }
    }

    /**
     * 打印所有学生所修的课程里的老师的名字
     * flatMap map,map
     */
    fun rxjava06() {
        Observable.fromIterable(Util.getAllStudents())
            .flatMapIterable { it.courses }
            .map { it.teacher }
            .map { it.name }
            .subscribe { Log.i(TAG,"====rxjava06:$it") }

    }

    /**
     * 打印所有 课程难度 > 1的所有课程 老师的年龄
     * flatMap map filter
     */
    fun rxjava07() {
        Observable.fromIterable(Util.getAllStudents())
            .flatMapIterable { it.courses }
            .filter { it.difficulty > 1 }
            .map { it.teacher }
            .map { it.age }
            .subscribe { Log.i(TAG,"rxjava07=老师年龄：$it") }
    }
}