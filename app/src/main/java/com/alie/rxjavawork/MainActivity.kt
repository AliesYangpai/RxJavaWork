package com.alie.rxjavawork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

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
//        rxJava01()
//        rxJava02()
//        rxJava03()
//        rxJava04()
//        rxJava05()
//        rxJava06()
//        rxJava07()
//        rxJava08()
//        rxJava09()
//        rxJava10()
//        rxJava11()
//        rxJava12()
//        rxJava13()
//        rxJava14()
//        rxJava15()
//        rxJava16()
//        rxJava17()
//        rxJava18()
//        rxJava19()
//        rxJava20()
//        rxJava21()
//        rxJava22()
//        rxJava23()
//        rxJava25()
//        rxJava26()
//        rxJava27()
//        rxJava28()
//        rxJava29()
//        rxJava40()
//        rxJava41()
//        rxJava42()
//        rxJava43()
//        rxJava44()
//        rxJava45()
//        rxJava46()
//        rxJava47()
        rxJava48()
    }

    /**
     * ============基本练习开始================
     */

    /**
     * 01初步使用，发射一下事件
     */
    private fun rxJava01() {
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
     * map使用
     * 02输出所有学生的姓名
     */
    fun rxJava02() {

        // 写法1：
        Observable.fromIterable(Util.getAllStudents())
            .map { student -> student.name }
            .subscribe { name -> Log.i(TAG, "===rxJava02=name:$name") }


        // 写法2：(他娘的，map变换在kotlin版本中这么简单，直接就it.name ~~~)
        val subscribe =
            Observable.fromIterable(Util.getAllStudents())
                .map { it.name }
                .subscribe { Log.i(TAG, "===rxJava02=name:$it") }
    }

    /**
     * map map
     * 输出所有学生的鞋的名称
     * 03(实在想不到例子了）
     */
    fun rxJava03() {
        Observable.fromIterable(Util.getAllStudents())
            .map { it.cloth }
            .map { it.shoes }
            .subscribe {
                Log.i(TAG, "====rxJava03,shoes:$it")
            }
    }

    /**
     * map map filter
     * 输出所有性别是女的学生的鞋~（只能想到这种业务了）
     */
    fun rxJava04() {
        Observable.fromIterable(Util.getAllStudents())
            .filter { it.gender.equals("女") }
            .map { it.cloth }
            .map { it.shoes }
            .subscribe { Log.i(TAG, "女生的鞋：$it") }
    }

    /**
     * flatMap(flatMapIterable) + map
     * 打印所有学生所修的课程名称
     */
    fun rxJava05() {
        // 写法1：在flatMap中返回Observable.fromIterable(it.courses)

        Observable.fromIterable(Util.getAllStudents())
            .flatMap { Observable.fromIterable(it.courses) }
            .map { it.name }
            .subscribe { Log.i(TAG, "rxJava05:allcourse:$it") }
        // 写法2：直接使用哪个
        Observable.fromIterable(Util.getAllStudents())
            .flatMapIterable { it.courses }
            .map { it.name }
            .subscribe { Log.i(TAG, "rxJava05:allcourse:$it") }
    }

    /**
     * flatMap map,map
     * 打印所有学生所修的课程里的老师的名字
     */
    fun rxJava06() {
        Observable.fromIterable(Util.getAllStudents())
            .flatMapIterable { it.courses }
            .map { it.teacher }
            .map { it.name }
            .subscribe { Log.i(TAG, "====rxJava06:$it") }

    }

    /**
     * flatMap map filter
     * 打印所有 课程难度 > 1的所有课程 老师的年龄
     */
    fun rxJava07() {
        Observable.fromIterable(Util.getAllStudents())
            .flatMapIterable { it.courses }
            .filter { it.difficulty > 1 }
            .map { it.teacher }
            .map { it.age }
            .subscribe { Log.i(TAG, "rxJava07=老师年龄：$it") }
    }

    /**
     * Flowable
     * 打印所有学生姓名
     */
    fun rxJava08() {
        Flowable.fromIterable(Util.getAllStudents())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.i(TAG, "rxJava08=名称：${it.name}")
            }
    }

    /**
     * single
     */
    fun rxJava09() {
        Single.just(5 * 6).subscribe(object : SingleObserver<Int> {
            override fun onError(e: Throwable) {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onSuccess(t: Int) {
                Log.i(TAG, "====rxJava09:$t")
            }

        })
    }

    /**
     * ============基本练习结束================
     */

    /**
     * ==============创建型操作符 开始======================
     */

    /**
     * create
     * 创建一个被观察者
     */
    fun rxJava10() {
        Observable
            .create<String> { it.onNext("你好，这里是 创建型操作符") }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.i(TAG, "===rxJava10 $it")
            }
    }

    /**
     * just
     * 创建一个被观察者，一次发送10个以内的事件
     */
    fun rxJava11() {
        Observable.just(2 * 2, 3 * 3, 4 * 4, 5 * 5)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.i(TAG, "======rxJava11 $it")
            }
    }

    /**
     * fromArray
     * 创建一个被观察者，发送数组
     */
    fun rxJava12() {

        /**
         * todo 这里的fromArray传参是可变数组，它是按照元素计算，因此，传入arr也会在消费的时候直接返回出来
         * todo 而不是遍历
         */

        val arr = intArrayOf(2, 5)
        Observable.fromArray(1, 2, 3)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {

            }

    }

    /**
     * fromCallable
     * 在fromCallable{} 大括号中，直接写call中的代码即可，
     * 这里是lamuda表达式，因此，最后一行就lamuda的返回值
     */
    fun rxJava13() {
//        Observable.fromCallable(object : Callable<String> {
//            override fun call(): String {
//                return "callable"
//            }
//
//        })
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//            Log.i(TAG,"=====rxJava13:$it")
//        }

        /**
         * 在fromCallable{} 大括号中，直接写call中的代码即可，
         * 这里是lamuda表达式，因此，最后一行就lamuda的返回值
         */
        Observable.fromCallable {
            Log.i(TAG, "Callable的call方法调用啦xxxxx~~~当前线程： ${Thread.currentThread().name}")
            "yeah baby"
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.i(TAG, "=====rxJava13:$it")
            }

    }

    /**
     * fromIterable
     * distinct 用于搜搜结果去重复
     * 遍历一个集合
     * 打印所有 课程难度 > 1的所有课程 老师的年龄
     */
    fun rxJava14() {
        Observable.fromIterable(Util.getAllStudents())
            .flatMapIterable { it.courses }
            .filter { it.difficulty > 1 }
            .map { it.teacher }
            .distinct { it.age }
            .subscribe {
                Log.i(TAG, "====rxJava14：teacherAge:${it?.age}")
            }
    }

    /**
     * timer
     * 延迟1 min进行打印 这里仅仅执行一次
     */
    fun rxJava15() {
        Observable.timer(1, TimeUnit.MINUTES).subscribe {
            Log.i(TAG, "======rxJava15 $it")
        }
    }

    /**
     * interval
     * 延迟500 ms 并且每隔1000 ms 打印一次数据
     * （这里会永远打印下去，如果要停止，需使用过滤操作符 take,后面介绍）
     */
    fun rxJava16() {
        Observable
            .interval(500, 1000, TimeUnit.MILLISECONDS)
            .subscribe { Log.i(TAG, "=======rxJava16 你好，我的朋友") }

    }

    /**
     * range
     * 0:代表第一个数
     * 6：代表数量，（这里就是 从0开始算产生连续的6个数）
     * 数组可以这么应用一波
     */
    fun rxJava17() {
        Flowable.range(0, 6)
            .subscribe { Log.i(TAG, "=====rxJava17 $it") }
    }


    /**
     * ==============创建型操作符 结束======================
     */
///////////////////////////////////////////////////////////////////////

    /**
     * ==============转换操作符 开始======================
     */

    /**
     * map
     * 打印所有学生衣服的名称
     */
    fun rxJava18() {
        Observable.fromIterable(Util.getAllStudents())
            .map { it.cloth }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                println("=====rxJava18 ${it?.jacket}")
            }
    }

    /**
     * flatMap
     * 打印所有老师的年龄，去重
     */
    fun rxJava19() {
        Observable.fromIterable(Util.getAllStudents())
            .flatMapIterable { it.courses }
            .map { it.teacher }
            .distinct { it.age }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                println("=====rxJava19 ${it?.age}")
            }
    }

    /**
     * concatMap
     * 打印所有老师的姓名 有序转化
     */
    fun rxJava20() {
        Observable.fromIterable(Util.getAllStudents())
            .concatMapIterable { it.courses }
            .map { it.teacher }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                println("=====rxJava20 ${it?.name}")
            }
    }

    /**
     * buffer
     * 这个实际上就是打包发送
     * 将0-49这50个元素 每10个一发
     */
    fun rxJava21() {
        Observable.range(0, 49)
            .buffer(10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                println("=====rxJava21 ${it}")
            }
    }

    /**
     * groupBy
     * 按照一定逻辑进行分组，最终结果类似Map<key,Observable>
     */
    fun rxJava22() {
//        Observable.fromIterable(Util.getAllStudents())
//            .flatMapIterable { it.courses }
//            .map { it.teacher }
//            .groupBy { it.gender }
//            .subscribe { groupedObservable ->
//                println("===rxJava22() key ${groupedObservable.key}")
//                groupedObservable.subscribe { teacher ->
//                    println("===rxJava22() teacherName :${teacher?.name}")
//                }
//            }

        Observable.range(12, 25).groupBy {
            when {
                it < 18 -> "少年组"
                else -> "成年组"
            }
        }.subscribe {
            println("=rxJava22==key===${it.key}")
            it.subscribe { count ->
                println("=rxJava22==count===$count")
            }
        }
    }

    /**
     * scan
     * 将数据以一定的逻辑聚合起来
     * 这里我们实现一个数据累加的逻辑
     */
    fun rxJava23() {
        Observable.range(1, 10)
            .scan { t1, t2 ->
                println("===rxJava23 in scan t1:$t1 t2:$t2")
                t1 + t2
            }
            .subscribe {
                println("====rxJava23() in subscrib:$it")
            }
    }

    /**
     * window
     * 这个类似于buffer,不同于buffer的是，这个方法将结果封装成Observerl发射出来 （buffer是封装成list）
     * //todo 待补充
     */
    fun rxJava24() {
    }
    /**
     * ==============转换操作符 结束======================
     */

    ///////////////////////////////////////////////////////////////////////


    /**
     * ==============组合操作符 开始======================
     */

    /**
     * concat
     * 将多个观察者（Observer）组合在一起 【按顺序】 一并发射出来
     * concat(observer1,observer2,observer3,observer4) 此法最多4个观察者参数
     */
    fun rxJava25() {
        Observable.concat(
            Observable.fromIterable(Util.getAllCurseA()),
            Observable.fromIterable(Util.getAllCurseB()),
            Observable.fromIterable(Util.getAllCurseC()),
            Observable.fromIterable(Util.getAllCurseD())
        ).subscribe {
            println("===rxjava25 课程名称：${it.name}")
        }
    }

    /**
     * concatArray
     * 将多个观察者（Observer）组合在一起 【按顺序】 一并发射出来
     * 【个人理解】concat和concatArray选型的时候，可以直接选择的concatArray(concat也是调用concatArray)
     */
    fun rxJava26() {
        Observable.concatArray(
            Observable.fromIterable(Util.getAllCurseA()),
            Observable.fromIterable(Util.getAllCurseB()),
            Observable.fromIterable(Util.getAllCurseC()),
            Observable.fromIterable(Util.getAllCurseD()),
            Observable.fromIterable(Util.getAllCurseB()),
        ).subscribe {
            println("===rxjava26 课程名称：${it.name}")
        }
    }

    /**
     * merge (mergeArray与concatArray类似)
     * 基本与concat一致，只不过merge是并行发送，因此不保证发送的observel的发射顺序
     * （就目前我的自测来看，数据暂时是一致的）
     */
    fun rxJava27() {
        Observable.merge(
            Observable.just(1, 2, 3),
            Observable.just(4, 5, 6),
            Observable.just(7, 8, 9)
        ).subscribe {
            println("===rxJava27:$it")
        }
    }

    /**
     * zip
     * 将多个被观察者 按顺序组合起来，最终发送的事件数量与源Observable中最少事件的数量为准
     * 可以看到，int集合长度4 String集合长度3，结果只打了3次
     */
    fun rxJava28() {
        Observable.zip(
            Observable.fromIterable(Util.getAllNumbersInt()),
            Observable.fromIterable(Util.getAllNumberString()),
            { t1, t2 -> t1.toString() + t2 }
        ).subscribe {
            println("====rxJava28:$it")
        }
    }

    /**
     * reduce
     * reduce与scan类似
     * scan:扫描一次发射一次 一共发射多次
     * reduce:一次性全都处理完，再发射，仅仅发射一次
     * 个人理解，这里的reduce是相对于scan的事件发射次数来命名的
     */
    fun rxJava29() {
        Observable.just(1, 2, 3, 4)
            .reduce { t1, t2 -> t1 + t2 }
            .subscribe {
                println("===rxJava29 $it")
            }
    }
    /**
     * ==============组合操作符 结束======================
     */


    ///////////////////////////////////////////////////////////////////////

    /**
     * ==============过滤操作符 开始======================
     */
    /**
     * filter
     * 打印出所有性别为女的学生 所修的课程的老师名称
     *
     */
    fun rxJava40() {
        Observable.fromIterable(Util.getAllStudents())
            .filter { it.gender == "女" }
            .concatMapIterable { it.courses }
            .map { it.teacher }
            .distinct() // 过滤重复事件
            .subscribe {
                println("===rxJava40 ${it?.name}")
            }
    }

    /**
     * ofType
     * 这里的oftype是指不同的事件类型过滤，如果是一种事件，则效果很差~
     */
    fun rxJava41() {
        Flowable.just("a", 1, 2, "b", "c")
            .ofType(String::class.java)
            .subscribe {
                println("===rxjava41 $it")
            }
    }

    /**
     * skip
     * skip(1) 指跳过前1个
     * skip（2） 指跳过前2个
     * skipLast(2) 指跳过后两个
     */
    fun rxJava42() {
        Flowable.fromIterable(Util.getAllStudents())
            .skip(1)
            .skipLast(2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                println("===rxjava42 ${it.gender}")
            }
    }

    /**
     * distinct：过滤掉重复事件
     *
     */
    fun rxJava43() {
        Flowable.fromIterable(Util.getAllStudents())
            .filter { it.gender == "女" }
            .concatMapIterable { it.courses }
            .map { it.teacher }
            .distinct { it.name } // 按照名称过滤
            .subscribe {
                println("===rxJava43 ${it?.name}")
            }
    }

    /**
     * distinctUntilChanged：过滤掉连续重复的元素
     *
     * 输入结果：
     * "a","b","c","b","c","d"
     */
    fun rxJava44() {
        Flowable.just("a", "a", "a", "b", "c", "b", "c", "c", "d", "d")
            .distinctUntilChanged()
            .subscribe {
                println("===rxJava44 $it")
            }
    }

    /**
     * take:仅仅发射count个事件
     */
    fun rxJava45() {
        Observable.fromIterable(Util.getAllStudents())
            .concatMapIterable { it.courses }
            .distinct { it.name }
//            .take(4) // 这里只取前4个事件
            .takeLast(4) // 这里只去后4个事件
            .subscribe {
                println("===rxJava45 ${it.name}")
            }
    }

    /**
     * ==============过滤操作符 结束======================
     */

    ///////////////////////////////////////////////////////////////////////

    /**
     * ==============条件操作符 开始======================
     */

    /**
     * all 当事件序列都满足某个条件时候，返回true否则返回false
     * 判断所有课程的课时是否都是20
     */
    fun rxJava46() {
        Observable.fromIterable(Util.getAllStudents())
            .concatMapIterable { it.courses }
            .all { it.time == 20 }
            .subscribe { t1, t2 ->
                println("====rxJava46 t1 $t1, t2$t2")
            }
    }

    /**
     * takewhile 从左往右，一组事件序列中，只要出现不满足条件条件的序列就终止事件发射
     */
    fun rxJava47() {
        Observable.just(1, 2, 3, 4)
            .takeWhile { it <= 3 }
            .subscribe {
                println("===rxJava47:it$it ")
            }
    }

    /**
     * skipWhile 一组事件序列中，
     */
    fun rxJava48() {
        Observable.fromIterable(Util.getAllStudents())
            .concatMapIterable { it.courses }
            .map { it.teacher }
            .distinct { it.name }
            .skipWhile { it.age < 30 }
            .subscribe {
                println("===rxJava48 teacherName:${it?.name}")
            }

    }
    /**
     * ==============条件操作符 结束======================
     */
}