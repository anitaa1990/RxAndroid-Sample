package com.an.rxjava_sample;


import com.an.rxjava_sample.model.User;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava2.math.MathObservable;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Notification;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;
import io.reactivex.subjects.UnicastSubject;

public class ExampleUnitTest {

    @Test
    public void testCreateObservable() {

        final List<String> alphabets = new ArrayList<>();
        alphabets.add("A");
        alphabets.add("B");
        alphabets.add("C");
        alphabets.add("D");
        alphabets.add("E");
        alphabets.add("F");

        /*
         * Observable.create() -> We will need to call the
         * respective methods of the emitter such as onNext()
         * & onComplete() or onError()
         *
         * */
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) {

                try {

                    /*
                     * The emitter can be used to emit each list item
                     * to the subscriber.
                     *
                     * */
                    for (String alphabet : alphabets) {
                        emitter.onNext(alphabet);
                    }

                    /*
                     * Once all the items in the list are emitted,
                     * we can call complete stating that no more items
                     * are to be emitted.
                     *
                     * */
                    emitter.onComplete();

                } catch (Exception e) {

                    /*
                     * If an error occurs in the process,
                     * we can call error.
                     *
                     * */
                    emitter.onError(e);
                }
            }
        });

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext: " + o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        observable.subscribe(observer);
    }



    @Test
    public void testDeferObservable() {

        final List<String> alphabets = new ArrayList<>();
        alphabets.add("A");
        alphabets.add("B");
        alphabets.add("C");
        alphabets.add("D");
        alphabets.add("E");
        alphabets.add("F");

        /*
         * Observable.defer()
         *
         * */

    }



    @Test
    public void testFromObservable() {

        Observable.fromArray(new String[]{"A", "B", "C", "D", "E", "F"})
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String string) {
                        System.out.println("onNext: " + string);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testIntervalObservable() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        System.out.println("onNext: " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(10000);
    }


    @Test
    public void testJustObservable() {
        Observable.just(new String[]{"A", "B", "C", "D", "E", "F"})
                .subscribe(new Observer<String[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String[] strings) {
                        System.out.println("onNext: " + Arrays.toString(strings));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testRangeObservable() {
        Observable.range(2, 5)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testRepeatObservable() {
        Observable.range(2, 5)
                .repeat(2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testTimerObservable() throws InterruptedException {
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("onNext: " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(10000);
    }


    @Test
    public void testBufferObservable() {

        Observable.just("A", "B", "C", "D", "E", "F")
                .buffer(2)
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        System.out.println("onNext(): ");
                        for (String s : strings) {
                            System.out.println("String: " + s);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testMapObservable() throws InterruptedException {

        getOriginalObservable()
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(final Integer integer) {
                        return (integer * 2);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(2000);
    }


    @Test
    public void testFlatMapObservable() throws InterruptedException {

        getOriginalObservable()
                .flatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(final Integer integer) {
                        return getModifiedObservable(integer);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(2000);
    }


    private Observable<Integer> getOriginalObservable() {
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);

        return Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) {
                        for (Integer integer : integers) {

                            if (!emitter.isDisposed()) {
                                emitter.onNext(integer);
                            }
                        }

                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }

                });
    }

    private Observable<Integer> getModifiedObservable(final Integer integer) {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws InterruptedException {
                emitter.onNext((integer * 2));
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }


    @Test
    public void testSwitchMapObservable() throws InterruptedException {

        getOriginalObservable()
                .switchMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(final Integer integer) {
                        return getModifiedObservable(integer);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(2000);
    }


    @Test
    public void testConcatMapObservable() throws InterruptedException {

        getOriginalObservable()
                .concatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(final Integer integer) {
                        return getModifiedObservable(integer);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(2000);
    }


    @Test
    public void testGroupByObservable() {

        Observable.range(1, 10)
                .groupBy(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(Integer integer) {
                        return (integer % 2 == 0) ? true : false;
                    }
                })
                .subscribe(new Observer<GroupedObservable<Boolean, Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GroupedObservable<Boolean, Integer> booleanIntegerGroupedObservable) {
                        if (booleanIntegerGroupedObservable.getKey()) {
                            booleanIntegerGroupedObservable.subscribe(new Observer<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Integer integer) {
                                    System.out.println("onNext: " + integer);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testScanObservable() {

        Observable.range(1, 10)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
                        return (integer + integer2);
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testDistinctObservable() {

        Observable.just(10, 20, 20, 10, 30, 40, 70, 60, 70)
                .distinct()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testElementAtObservable() {

        Observable.just(1, 2, 3, 4, 5, 6)
                .elementAt(1)
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testFilterObservable() {

        Observable.just(1, 2, 3, 4, 5, 6)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return (integer % 2 == 0);
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testIgnoreElementsObservable() {

        Observable.range(1, 10)
                .ignoreElements()
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribed");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    @Test
    public void testSampleObservable() throws InterruptedException {

        Observable timedObservable = Observable
                .just(1, 2, 3, 4, 5, 6)
                .zipWith(Observable.interval(
                        0, 1, TimeUnit.SECONDS), (item, time) -> item);

        timedObservable
                .sample(2, TimeUnit.SECONDS)
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        Thread.sleep(10000);
    }


    @Test
    public void testSkipObservable() throws InterruptedException {

        Observable.just("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
                .skip(4)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testSkipLastObservable() throws InterruptedException {

        Observable.just("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
                .skipLast(4)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testTakeObservable() {

        Observable.just("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
                .take(4)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testTakeLastObservable() {

        Observable.just("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
                .takeLast(4)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testCombineLatestObservable() throws InterruptedException {

        Observable<Long> observable1 = Observable.interval(100, TimeUnit.MILLISECONDS);
        Observable<Long> observable2 = Observable.interval(100, TimeUnit.MILLISECONDS);

        Observable.combineLatest(observable1, observable2,
                (observable1Times, observable2Times) ->
                        "Observable1: " + observable1Times + " Observable2: " + observable2Times)
                .subscribe(item -> System.out.println(item));

        Thread.sleep(1000);
    }


    @Test
    public void testJoinObservable() throws InterruptedException {

        /*
         * We create two Observables: left & right which emits a value every 100 milliseconds.
         * We join left Observable to the right Observable.
         * The two integers emitted from both the Observables are added
         * & the result is printed.
         *
         * */
        Observable<Long> left = Observable
                .interval(100, TimeUnit.MILLISECONDS);

        Observable<Long> right = Observable
                .interval(100, TimeUnit.MILLISECONDS);

        left.join(right,
                aLong -> Observable.timer(0, TimeUnit.SECONDS),
                aLong -> Observable.timer(0, TimeUnit.SECONDS),
                (l, r) -> {
                    System.out.println("Left result: " + l + " Right Result: " + r);
                    return l + r;
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("onNext: " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(1000);
    }


    @Test
    public void testMergeObservable() throws InterruptedException {


        Observable<String> alphabets1 = Observable
                .interval(1, TimeUnit.SECONDS).map(id -> "A" + id);

        Observable<String> alphabets2 = Observable
                .interval(1, TimeUnit.SECONDS).map(id -> "B" + id);

        Observable.merge(alphabets1, alphabets2)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        Thread.sleep(5000);
    }


    @Test
    public void testConcatObservable() throws InterruptedException {


        Observable<String> alphabets1 = Observable
                .interval(1, TimeUnit.SECONDS).map(id -> "A" + id);

        Observable<String> alphabets2 = Observable
                .interval(1, TimeUnit.SECONDS).map(id -> "B" + id);

        Observable.concat(alphabets1, alphabets2)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        Thread.sleep(5000);
    }


    @Test
    public void testZipObservable() throws InterruptedException {


        Observable<String> alphabets1 = Observable
                .interval(1, TimeUnit.SECONDS).map(id -> "A" + id);

        Observable<String> alphabets2 = Observable
                .interval(1, TimeUnit.SECONDS).map(id -> "B" + id);

        Observable.zip(alphabets1, alphabets2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return (s + " " + s2);
            }
        })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        Thread.sleep(5000);
    }


    @Test
    public void testSwitchOnNextObservable() throws InterruptedException {


        Observable
                /* This is the outer observable. Items emitted here will be used to control the inner observable.
                 * Whenever it emits an item, the inner observable will stop its emission
                 * and a new one will be created.
                 */
                .switchOnNext(Observable.interval(600, TimeUnit.MILLISECONDS)

                        .map((aLong) -> {
                            /* This is the inner observable. It will emit items every 180ms.
                             * When the outer observable emits a new item (which is supposed to happen after 600ms)
                             * this one will be discarded and a new one will be taken in place.
                             * Since outer observable will emit items each 600ms, inner observable will have a chance to emit 3 items and
                             * then be discarded. */
                            return Observable.interval(180, TimeUnit.MILLISECONDS);
                        }))

                .subscribe(item -> System.out.println("onNext: " + item));

        Thread.sleep(5000);
    }


    @Test
    public void testDelayObservable() throws InterruptedException {


        Observable.just("A", "B", "C", "D", "E", "F")
                .delay(2, TimeUnit.SECONDS)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        Thread.sleep(5000);
    }


    @Test
    public void testDoObservable() throws InterruptedException {


        Observable.just("A", "B", "C", "D", "E", "F")
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println("doOnNext: " + s);
                    }
                })
                .subscribe();

        System.out.println("---------------");
        Observable.range(1, 5)
                .doOnEach(new Observer<Integer>() {

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Complete is called");
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe is called");
                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("doOnEach: " + value);
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) {
                        System.out.println("onUnSubscribe is called");
                    }
                })
                .subscribe();
    }


    @Test
    public void testMaterializeObservable() throws InterruptedException {


        Observable.just("A", "B", "C", "D", "E", "F")
                .materialize()
                .subscribe(new Observer<Notification<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Notification<String> stringNotification) {
                        /*
                         * From the notification object, we can check if the
                         * emitted item is:
                         * isOnNext() or isOnError() or isOnComplete()
                         *
                         * Here we can basically fetch items that are successful
                         * & omit items that resulted in error.
                         *
                         *  */
                        System.out.println(stringNotification.getValue());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testObserveOnObservable() {


        Observable.just("A", "BB", "CCC", "DDDD", "EEEEE", "FFFFFF") // UI
                .map(str -> str.length()) // UI
                .observeOn(Schedulers.computation()) //Changing the thread
                .map(length -> 2 * length)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("2 * length of string: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testSubscribeOnObservable() {


        Observable.range(1, 5)
                .map(i -> i * 100)
                .doOnNext(i -> {
                    System.out.println("Emitting " + i + " on thread " + Thread.currentThread().getName());
                })
                .subscribeOn(Schedulers.computation())
                .map(i -> i * 10)
                .subscribe(i -> {
                    System.out.println("Received " + i + " on thread " + Thread.currentThread().getName());
                });


    }


    @Test
    public void testTimeIntervalObservable() throws InterruptedException {

        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .timeInterval()
                .subscribe(new Subject<Timed<Long>>() {
                    @Override
                    public boolean hasObservers() {
                        return false;
                    }

                    @Override
                    public boolean hasThrowable() {
                        return false;
                    }

                    @Override
                    public boolean hasComplete() {
                        return false;
                    }

                    @Override
                    public Throwable getThrowable() {
                        return null;
                    }

                    @Override
                    protected void subscribeActual(Observer<? super Timed<Long>> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Timed<Long> longTimed) {
                        System.out.println("onNext: " + longTimed);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(3000);
    }


    @Test
    public void testTimeoutObservable() throws InterruptedException {

        Observable.just(1l, 2l, 3l, 4l, 5l, 6l)
                .timer(1, TimeUnit.SECONDS)
                .timeout(500, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("onNext: " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        System.out.println("onError: ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(3000);
    }


    @Test
    public void testTimestampObservable() throws InterruptedException {

        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .timestamp()
                .subscribe(new Observer<Timed<Long>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Timed<Long> longTimed) {
                        System.out.println(longTimed);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(3000);
    }


    @Test
    public void testUsingObservable() throws InterruptedException {

        Observable.using(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return "Example";
                    }
                },

                new Function<String, ObservableSource<Character>>() {
                    @Override
                    public ObservableSource<Character> apply(String s) {
                        return Observable.create(o -> {
                            for (Character c : s.toCharArray()) {
                                o.onNext(c);
                            }
                            o.onComplete();
                        });
                    }
                },

                new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println("Disposable: " + s);
                    }
                }
        )

                .subscribe(new Observer<Character>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Character character) {
                        System.out.println("onNext: " + character);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testAllObservable() {

        Observable.just(0, 1, 2, 3, 4, 0, 6, 0)
                .all(item -> item > 0)
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        System.out.println("onNext: " + aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    @Test
    public void testAmbObservable() throws InterruptedException {

        Observable<Integer> observable1 = Observable.timer(4, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Long aLong) throws Exception {
                        return Observable.just(10, 20, 30, 40, 50);
                    }
                });

        Observable<Integer> observable2 = Observable.timer(3, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Long aLong) throws Exception {
                        return Observable.just(100, 200, 300, 400, 500);
                    }
                });

        Observable
                .ambArray(observable1, observable2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(5000);
    }


    @Test
    public void testContainsObservable() {

        Observable.just(1, 2, 3, 4, 5, 6)
                .contains(10)
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        System.out.println("Does list contain value 10: " + aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    @Test
    public void testDefaultIfEmptyObservable() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                int num = (int) (Math.random() * 10);
                if (num % 2 == 0) {
                    emitter.onNext(num);
                }
                emitter.onComplete();
            }
        })
                .defaultIfEmpty(-10)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testSequenceEqualObservable() {

        Observable<Integer> observable1 = Observable
                .just(1, 2, 3, 4, 5, 6);

        Observable<Integer> observable2 = Observable
                .just(1, 2, 3, 4, 5, 6);

        Observable.sequenceEqual(observable1, observable2)
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        System.out.println("Are the two sequences equal: " + aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    @Test
    public void testSkipUntilObservable() {

        Observable<Integer> observable1 = Observable
                .create(emitter -> {
                    for(int i=0; i<= 6; i++) {
                        Thread.sleep(1000);
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                });

        Observable<Integer> observable2 = Observable
                .timer(3, TimeUnit.SECONDS)
                .flatMap(__ -> Observable.just(11, 12, 13, 14, 15, 16));

        observable1.skipUntil(observable2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        Thread.sleep(5000);
    }


    @Test
    public void testSkipWhileObservable() {

        Observable
                .create(emitter -> {
                    for(int i=0; i<= 6; i++) {
//                        Thread.sleep(1000);
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                })
                .skipWhile(new Predicate<Object>() {
                    @Override
                    public boolean test(Object o) throws Exception {
                        return (((Integer)o < 2));
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testTakeUntilObservable() {

        Observable<Integer> observable1 = Observable
                .create(emitter -> {
                    for(int i=0; i<= 6; i++) {
                        Thread.sleep(1000);
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                });

        Observable<Integer> observable2 = Observable
                .timer(3, TimeUnit.SECONDS)
                .flatMap(__ -> Observable.just(11, 12, 13, 14, 15, 16));

        observable1.takeUntil(observable2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testTakeWhileObservable() {

        Observable
                .create(emitter -> {
                    for(int i=0; i<= 6; i++) {
                        Thread.sleep(1000);
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                })
                .takeWhile(new Predicate<Object>() {
                    @Override
                    public boolean test(Object o) {
                        return (((Integer)o < 2));
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testAverageObservable() {

        Observable<Integer> numberObservable = Observable.fromArray(1, 2, 3, 4, 5);

        MathObservable.averageDouble(numberObservable)
                .subscribe(new Observer<Double>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Double aDouble) {
                        System.out.println("Average: " + aDouble);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testCountObservable() {

        Observable.just(1, 2, 3, 4, 5)
                .count()
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Long aLong) {
                        System.out.println("Count: " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    @Test
    public void testMaxObservable() {

        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);

        MathObservable
             .max(observable)
             .subscribe(new Observer<Integer>() {
                 @Override
                 public void onSubscribe(Disposable d) {

                 }

                 @Override
                 public void onNext(Integer integer) {
                     System.out.println("Max value: " + integer);
                 }

                 @Override
                 public void onError(Throwable e) {

                 }

                 @Override
                 public void onComplete() {

                 }
             });
    }


    @Test
    public void testMinObservable() {

        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);

        MathObservable
                .min(observable)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("Max value: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testReduceObservable() {

        Observable.just(1, 2, 3, 4, 5)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
                        return (integer * integer2);
                    }
                })
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        System.out.println("Product of numbers from 1, 2, 3, 4, 5: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testSumObservable() {

        Observable<Integer> observable = Observable
                .just(1, 2, 3, 4, 5);

        MathObservable
                .sumInt(observable)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("Sum: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Test
    public void testSingleObservable() {

            Single.create(new SingleOnSubscribe<User>() {
                    @Override
                    public void subscribe(SingleEmitter<User> emitter) throws Exception {
                        User user = new User("Anitaa");
                        emitter.onSuccess(user);
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(User user) {
                        System.out.println(String.format("User with name '%s' successfully created ", user.getName()));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }


    @Test
    public void testMaybeObservable() {

        Maybe.create(new MaybeOnSubscribe<User>() {
            @Override
            public void subscribe(MaybeEmitter<User> emitter) {
                User user = new User("Anitaa");
                emitter.onSuccess(user);
            }
        })
                .observeOn(Schedulers.io())
                .subscribe(new MaybeObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(User user) {
                        System.out.println(String.format("User with name '%s' successfully created ", user.getName()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError is called: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete is called");
                    }
                });

    }


    @Test
    public void testCompletableObservable() {

        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) {
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete is called");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError is called" + e.getMessage());
                    }
                });
    }


    @Test
    public void testFlowableObservable() throws InterruptedException {

        Flowable.range(10, 1000)
                .subscribeOn(Schedulers.io())
                .reduce(1, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
                        return (integer + integer2);
                    }
                })
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe is called");
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        System.out.println("Success: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                });
    }


    @Test
    public void testSubjectExample() {

        /*
         * Step 1: Create an observable that emits an integer
         * from 1 to 5
         *  */
        Observable<Integer> observable = Observable.range(1, 5)
                .subscribeOn(Schedulers.io());

        /*
         * Step 2: Create a subject that observes
         * this emission from the observable. In this scenario,
         * the subject acts an an observer since it observes the
         * changes to the Observable.
         *  */
        ReplaySubject<Integer> subject = ReplaySubject.create();
        observable.subscribe(subject);


        /*
         * Step 3: In this scenario, the subject acts an an Observable,
         * since it emits each item from the original Observable.
         *  */
        subject.subscribe(s -> System.out.println("subscriber one: " + s));
        subject.subscribe(s -> System.out.println("subscriber two: " + s));
    }


    @Test
    public void testSubjectMulticastExample() {

        /*
         * Step 1: Create an observable that emits an integer
         * from 1 to 5. Each item is squared by itself before it is
         * emitted.
         *  */
        Observable<Integer> observable = Observable.range(1, 5)
                .subscribeOn(Schedulers.io())
                .map(integer -> {
                    System.out.println(String.format("Squaring %d with itself", integer));
                    return integer * integer;
                });

        /*
         * Step 2: Create a subject that observes
         * this emission from the observable.
         *  */
        ReplaySubject<Integer> subject = ReplaySubject.create();
        observable.subscribe(subject);


        /*
         * Step 3: We are subscribing two subscribers to the Subject.
         *  */
        subject.subscribe(s -> System.out.println("subscriber one: " + s));
        subject.subscribe(s -> System.out.println("subscriber two: " + s));
    }


    @Test
    public void testSubjectWithoutMulticastExample() throws InterruptedException {

        /*
         * Step 1: Create an observable that emits an integer
         * from 1 to 5. Each item is squared by itself before it is
         * emitted.
         *  */
        Observable<Integer> observable = Observable.range(1, 5)
                .subscribeOn(Schedulers.io())
                .map(integer -> {
                    System.out.println(String.format("Squaring %d with itself", integer));
                    return integer * integer;
                });

        /*
         * Step 2: We are subscribing two subscribers to the Observable.
         *  */
        observable.subscribe(s -> System.out.println("subscriber one: " + s));

        Thread.sleep(90);
        observable.subscribe(s -> System.out.println("subscriber two: " + s));
    }


    @Test
    public void testSubjectAsHotObservablesExample() {

        /*
         * Step 1: Create a subject and emit a
         * single integer from the subject. (Subject is acting
         * as an Observable in this case)
         * */
        PublishSubject<Integer> pSubject = PublishSubject.create();
        pSubject.onNext(0);


        /*
         * Step 2: Subscribe to the Subject - emissions from the subject
         * will be printed
         * */
        pSubject.subscribe(it -> System.out.println("Observer 1 onNext: " + it),
                (Throwable onError) -> { },
                () -> {},
                on1 -> System.out.println("Observer 1 onSubscribe"));

        /*
         * Step 3: Again emit values from subject.
         * */
        pSubject.onNext(1);
        pSubject.onNext(2);


        /*
         * Step 4: Again observe the emissions by adding
         * another Observer to the Subject.
         * */
        pSubject.subscribe(it -> System.out.println("Observer 2 onNext: " + it),
                (Throwable onError) -> { },
                () -> {},
                on1 -> System.out.println("Observer 2 onSubscribe"));


        pSubject.onNext(3);
        pSubject.onNext(4);
    }


    @Test
    public void testBehaviorSubjectExample() {

        BehaviorSubject<Integer> pSubject = BehaviorSubject.create();
        pSubject.onNext(0);


        pSubject.subscribe(it -> System.out.println("Observer 1 onNext: " + it),
                (Throwable error) -> { }, () -> {},
                on1 -> System.out.println("Observer 1 onSubscribe"));

        pSubject.onNext(1);
        pSubject.onNext(2);


        pSubject.subscribe(it -> System.out.println("Observer 2 onNext: " + it),
                (Throwable error) -> { }, () -> {},
                on1 -> System.out.println("Observer 2 onSubscribe"));

        pSubject.onNext(3);
        pSubject.onNext(4);
    }

    @Test
    public void testReplaySubjectExample() {

        ReplaySubject<Integer> pSubject = ReplaySubject.create();
        pSubject.onNext(0);


        pSubject.subscribe(it -> System.out.println("Observer 1 onNext: " + it),
                (Throwable error) -> { }, () -> {},
                on1 -> System.out.println("Observer 1 onSubscribe"));

        pSubject.onNext(1);
        pSubject.onNext(2);


        pSubject.subscribe(it -> System.out.println("Observer 2 onNext: " + it),
                (Throwable error) -> { }, () -> {},
                on1 -> System.out.println("Observer 2 onSubscribe"));

        pSubject.onNext(3);
        pSubject.onNext(4);
    }

    @Test
    public void testAsyncSubjectExample() {

        AsyncSubject<Integer> pSubject = AsyncSubject.create();
        pSubject.onNext(0);


        pSubject.subscribe(it -> System.out.println("Observer 1 onNext: " + it),
                (Throwable error) -> { }, () -> System.out.println("Observer 1 onComplete"),
                on1 -> System.out.println("Observer 1 onSubscribe"));

        pSubject.onNext(1);
        pSubject.onNext(2);


        pSubject.subscribe(it -> System.out.println("Observer 2 onNext: " + it),
                (Throwable error) -> { }, () -> System.out.println("Observer 2 onComplete"),
                on1 -> System.out.println("Observer 2 onSubscribe"));

        pSubject.onNext(3);
        pSubject.onNext(4);

        /* This is very important in AsyncSubject  */
        pSubject.onComplete();
    }


    @Test
    public void testUnicastSubjectExample() {

        Observable<Integer> observable = Observable.range(1, 5)
                .subscribeOn(Schedulers.io());


        UnicastSubject<Integer> pSubject = UnicastSubject.create();
        observable.subscribe(pSubject);


        pSubject.subscribe(it -> System.out.println("onNext: " + it));
    }
}