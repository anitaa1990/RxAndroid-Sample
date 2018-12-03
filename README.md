# RxAndroid-Sample
A list of concise write ups on the implementation of RxJava in Android

## Reactive Programming
* <b>Reactive Programming</b> is a programming paradigm oriented around data flows and the propagation of change i.e. it is all about responding to value changes. For example, let’s say we define x = y+z. When we change the value of y or z, the value of x automatically changes. This can be done by observing the values of y and z.

* <b>Reactive Extensions</b> is a library that follows Reactive Programming principles to compose asynchronous and event-based programs by using observable sequence.

* <b>RxJava</b> is a Java based implementation of Reactive Programming.

* <b>RxAndroid</b> is specific to Android platform which utilises some classes on top of the RxJava library.

## RxJava Basics
The building blocks of RxJava are:

* [Observable](http://reactivex.io/documentation/observable.html): class that emits a stream of data or events. i.e. a class that can be used to perform some action, and publish the result.
   ```
   Observable observable = Observable.just("A", "B", "C", "D", "E", "F");
   ```
   
* [Observer](http://reactivex.io/RxJava/javadoc/io/reactivex/Observer.html): class that receivers the events or data and acts upon it. i.e. a class that waits and watches the Observable, and reacts whenever the Observable publishes results. The Observer has 4 interface methods to know the different states of the Observable.
    * ```onSubscribe()```: This method is invoked when the Observer is subscribed to the Observable.
    * ```onNext()```: This method is called when a new item is emitted from the Observable.
    * ```onError()```: This method is called when an error occurs and the emission of data is not successfully completed.
    * ```onComplete()```: This method is called when the Observable has successfully completed emitting all items.
    </br>
    
   ```
    new Observer() {
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
 
 
## RxJava Operators
Operators allow you to manipulate the data that was emitted or create new Observables.

### Operators for creating Observables
* [Create](http://reactivex.io/documentation/operators/create.html) — This operator creates an Observable from scratch by calling observer methods programmatically. An emitter is provided through which we can call the respective interface methods when needed. The ```create()``` method does not have an option to pass values. So we have to create the list beforehand and perform operations on the list inside the ```onNext()``` method. [Sample Implementation](https://gist.github.com/anitaa1990/247ec8e276b935bf8b574a37f99af81e#file-createobservable-java)

* [Defer](http://reactivex.io/documentation/operators/defer.html) — This operator does not create the Observable until the Observer subscribes. The only downside to defer() is that it creates a new Observable each time you get a new Observer. create() can use the same function for each subscriber, so it’s more efficient. The [sample implementation](https://gist.github.com/anitaa1990/b086dc832c6a3caeb27bf3547ec291bf#file-deferobservable-java) creates an Observable that emits a value.

* [From](http://reactivex.io/documentation/operators/from.html) — This operator creates an Observable from set of items using an Iterable, which means we can pass a list or an array of items to the Observable and each item is emitted one at a time. Some of the examples of the operators include ```fromCallable()```, ```fromFuture()```, ```fromIterable()```, ```fromPublisher()```, ```fromArray()```. The [sample implementation](https://gist.github.com/anitaa1990/fdf0b8cd826e7a67343bac23f53d95f6#file-fromobservable-java) will print each item from the array one by one. The order is also preserved.

* [Interval](http://reactivex.io/documentation/operators/interval.html) — This operator creates an Observable that emits a sequence of integers spaced by a particular time interval. The [sample implementation](https://gist.github.com/anitaa1990/f249bcd1be6f562220d923b14e073e41#file-intervalobservable-java) will print values from 0 after every second.

* [Just](http://reactivex.io/documentation/operators/just.html) — This operator takes a list of arguments (maximum 10) and converts the items into Observable items. just() makes only 1 emission. For instance, If an array is passed as a parameter to the just() method, the array is emitted as single item instead of individual numbers. Note that if you pass null to just(), it will return an Observable that emits null as an item. The [sample implementation](https://gist.github.com/anitaa1990/846ca3d25a7f714a5e7a43b1ced36c94#file-justobservable-java) will print the entire list in a single emission.

<b>Note:</b>Difference between ```Observable.from()``` and ```Observable.just()``` — For the same input, if you see the sample code, ```Observable.just()``` emits only once whereas ```Observable.from()``` emits n times i.e. the length of the array.

* [Range](http://reactivex.io/documentation/operators/range.html) — This operator creates an Observable that emits a range of sequential integers. The function takes two arguments: the starting number and length. The [sample implementation](RangeObservable.java) has a starting number of 2 and a range of 5 numbers, so it will print values from 2 to 6.

* [Repeat](http://reactivex.io/documentation/operators/repeat.html) — This operator creates an Observable that emits a particular item or sequence of items repeatedly. There is an option to pass the number of repetitions that can take place as well. The [sample implementation](https://gist.github.com/anitaa1990/ac53759e1adab264ab467c83de1069c5#file-repeatobservable-java) will print the same values as the previous ```range()``` operator but since the repeat is specified as 2, the same values will be printed twice.

* [Timer](http://reactivex.io/documentation/operators/timer.html) — This operator creates an Observable that emits one particular item after a span of time that you specify. The [sample implementation](https://gist.github.com/anitaa1990/ceed36dd262b4d0e8a8754e1e8b6bce3#file-timerobservable-java) will emit only once after a 1 second delay.

<b>Note:</b>Difference between ```Observable.interval()``` and ```Observable.timer()``` — ```timer()``` emits just a single item after a delay whereas ```interval()``` operator, on the other hand, will emit items spaced out with a given interval.


### Operators for Transforming Observables
* [Buffer](http://reactivex.io/documentation/operators/buffer.html) — This operator periodically gather items from an Observable into bundles and emit these bundles rather than emitting the items one at a time. The [sample implementation](https://gist.github.com/anitaa1990/44f8bdafb9a52ebccd34b587380beec7#file-bufferobservable-java) will emit 2 items at a time since the buffer is specified as 2.

* [Map](http://reactivex.io/documentation/operators/map.html) — This operator transforms the items emitted by an Observable by applying a function to each item. map() operator allows for us to modify the emitted item from the Observable and then emits the modified item. In the [sample implementation](https://gist.github.com/anitaa1990/07e28a7310fc2d156b2300d8d586a997#file-mapobservable-java) we have a list of integer values. Using ```map()``` operator, each integer in the list is multiplied by 2 and the result is emitted. <b>Notice that the order of insertion is maintained during emission</b>.
    
* [FlatMap](http://reactivex.io/documentation/operators/flatmap.html) — This operator transforms each item emitted by an Observable but instead of returning the modified item, it returns the Observable itself which can emit data again. In other words, they merge items emitted by multiple Observables and returns a single Observable. The important difference between FlatMap and other transformation operators is that the order in which the items are emitted is not maintained. In the [sample implementation](https://gist.github.com/anitaa1990/f005734ab8e77442f5dfab4559d92105#file-flatmapobservable-java), we are going to follow the same test with the same conditions except instead of ```map()``` we are going to use ```flatMap()```. <b>Notice that the order of insertion is not maintained</b>.

* SwitchMap — Whenever a new item is emitted by the Observable, it will unsubscribe to the Observable that was generated from the previously emitted item and begin only mirroring the current one. In other words, it returns the latest Observable and emits the items from it. In the [sample implementation](https://gist.github.com/anitaa1990/565d8896a27827bc0847d7d062169c9c#file-switchmapobservable-java), we are going to follow the same test with the same conditions, except this time instead of ```flatMap()``` we are going to use ```switchMap()```. The output of the below code will be 12 because: we are passing a list of integers (1,2,3,4,5,6) and using ```switchMap()```, multiplying the each integer by 2. ```switchMap()``` always returns the latest Observable and emits from it.

* ConcatMap — This operator functions the same way as ```flatMap()```, the difference being in ```concatMap()``` the order in which items are emitted are maintained. One disadvantage of ```concatMap()``` is that it waits for each observable to finish all the work until next one is processed. In the [sample implementation](https://gist.github.com/anitaa1990/212e226d4fe2951477aaa9260d374647#file-concatmapobservable-java), we are going to follow the same test with the same conditions, except this time instead of ```switchMap()``` we are going to use ```concatMap()```.
    
<b>Scenarios we can use the different operators</b>:
* <b>Map</b> operator can be used when we fetch items from the server and need to modify it before emitting to the UI.
* <b>FlatMap</b> operator can be used when we know that the order of the items are not important.
* <b>SwitchMap</b> is best suited for scenarios such as a feed page, when pull to refresh is enabled. When user refreshes the screen, the older feed response is ignored and only the latest request results are emitted to the UI when using a SwitchMap.   

* [GroupBy](http://reactivex.io/documentation/operators/groupby.html) — This operator divides an Observable into a set of Observables that each emit a different group of items from the original Observable, organised by key. The [sample implementation](https://gist.github.com/anitaa1990/27061ee33dc05ebf9341f37a3cc71b0f#file-groupbyobservable-java) will create an Observable with range of 1 to 10 numbers. We use the ```groupBy()``` operator to emit only even numbers from the list.

* [Scan](http://reactivex.io/documentation/operators/scan.html) — This operator Transform each item into another item, like you did with map. But also include the “previous” item when you get around to doing a transform. The [sample implementation](https://gist.github.com/anitaa1990/2c92a706e58348e1c323a22ec71f6971#file-scanobservable-java) we have a range of 1 to 10 numbers. The ```scan()``` operator emits two integers at a time. The below code adds the two integers that is emitted and emits the sum.


### Operators for Filtering Observables
* [Debounce](http://reactivex.io/documentation/operators/debounce.html) — This operator only emits an item from an Observable if a particular timespan has passed without it emitting another item. Let’s explain this operator with an example. Let’s say we are implementing a search feature in Android using ```AutoCompleteTextView```. Whenever a user enters a character, we would need to fetch the list of items corresponding to that character. If the user enters 10 characters, and if we are fetching the data from a backend api, then that would mean 10 api calls to the backend. With the ```debounce()``` operator, we can specify the wait time (for instance, 2 seconds). Then the Observable, will wait 2 seconds every time the user enters a character in the EditText. If the user types another character before the 2 seconds are up, then the Observable waits another 2 seconds. If the user does not enter another character at the end of 2 seconds, the rest api is called. There are lots of resources online for this implementation. Please check this [link](https://jayrambhia.com/notes/rxjava-debounce) and this [link](https://medium.com/@kurtisnusbaum/rxandroid-basics-part-2-6e877af352) for a sample implementation.

* [Distinct](http://reactivex.io/documentation/operators/distinct.html) — This operator suppresses duplicate items emitted by an Observable. The distinct operator works very well with primitive data types. But in order to work with a custom dataType, we need to override the ```equals()``` and ```hashCode()``` methods. The [sample implementation](https://gist.github.com/anitaa1990/d5e3104ef1df2cfac9902f55eefb5ec9#file-distinctobservable-java) provides an example of ```distinct()``` operator. When we pass a list of duplicate integer values, the Observable uses the ```distinct()``` operator to emit only unique values from the list.

* [ElementAt](http://reactivex.io/documentation/operators/elementat.html) — This operator emits only one item ’n’ emitted by an Observable. We can specify the position we need to emit using the ```elementAt``` operator. For instance, ```elementAt(0)``` will emit the first item in the list. In the [sample implementation](https://gist.github.com/anitaa1990/1bdcc2c1921dd3e48f3b9976571cca7c#file-elementatobservable-java) we can specify a list of integers and using the elementAt() operator, we can fetch the element at that particular index. If the index is not in the list, then nothing will be emitted.

* [Filter](http://reactivex.io/documentation/operators/filter.html) — This operator emits only those items from an Observable that pass a predicate test. In the [sample implementation](https://gist.github.com/anitaa1990/361b6493cdcb23b6e40e085409b69e00#file-filterobservable-java) or a list of integers from 1 to 6, we add a filter condition that filters only the even numbers and emits those integers.

* [IgnoreElements](http://reactivex.io/documentation/operators/ignoreelements.html) — This operator does not emit any items from an Observable but mirrors its termination notification (either ```onComplete``` or ```onError```). If you do not care about the items being emitted by an Observable, but you do want to be notified when it completes or when it terminates with an error, you can apply the ```ignoreElements()``` operator to the Observable, which will ensure that it will never call the observers’ ```onNext()``` methods. In the [sample implementation](https://gist.github.com/anitaa1990/b3297d71ee470e694ef3177936edb29c#file-ignoreelementsobservable-java) only the ```onComplete()``` method will be called once the emission of items is complete. There is no ```onNext()``` method.

* [Sample](http://reactivex.io/documentation/operators/sample.html) — This operator emits the most recent item emitted by an Observable within periodic time intervals. The Sample operator periodically looks at an Observable and emits whichever item it has most recently emitted since the previous sampling. In the [sample code](https://gist.github.com/anitaa1990/b1cd4708f4b40546cd1b4f25b2f5d36a#file-sampleobservable-java), we have a list of integers from 1 to 6. The Observable emits each integer every second. We can use the ```sample()``` operator to check the emitted items every 2 seconds and emit the latest value from the Observable.

* [Skip](http://reactivex.io/documentation/operators/skip.html) — skip(n) operator suppresses the first n items emitted by an Observable. The [sample code](https://gist.github.com/anitaa1990/8c3178561a503250a1ef4def52feaac8#file-skipobservable-java) demonstrates the use of ```skip()``` operator. Let’s say we have an Observable that emits the first 10 alphabets and if ``skip(4)`` operator is used, it skips the first 4 alphabets from the list and emits only the remaining 6 items. 

* [SkipLast](http://reactivex.io/documentation/operators/skiplast.html) — ```skipLast(n)``` operator suppresses the last n items emitted by an Observable. The [sample code](https://gist.github.com/anitaa1990/aa053d4e511769fefd2ca5b98369f1f3#file-skiplastobservable-java) demonstrates the use of ```skipLast()``` operator. Let’s say we have an Observable that emits the first 10 alphabets and if ```skipLast(4)``` operator is used, it skips the last 4 alphabets from the list and emits only the remaining 6 items.

* [Take](http://reactivex.io/documentation/operators/take.html) — ```take(n)``` operator is the exact opposite of Skip. It emit only the first n items emitted by an Observable. The [sample code](https://gist.github.com/anitaa1990/d65324327feb1ed6b0e2b4eb899b1c02#file-takeobservable-java) demonstrates the use of ```take()``` operator. Let’s say we have an Observable that emits the first 10 alphabets and if ```take(4)``` operator is used, it emits the first 4 alphabets from the list and skips the remaining 6 items.

* [TakeLast](http://reactivex.io/documentation/operators/takelast.html) — ```takeLast(n)``` operator emit only the last n items emitted by an Observable. The [sample code](https://gist.github.com/anitaa1990/e43b30a63966616a11932faf9d504462#file-takelastobservable-java) demonstrates the use of ```takeLast()``` operator. Let’s say we have an Observable that emits the first 10 alphabets and if ```takeLast(4)``` operator is used, it emits the last 4 alphabets from the list and skips the remaining 6 items.


### Operators for Combining Observables
* [CombineLatest](http://reactivex.io/documentation/operators/combinelatest.html) — This operator is used when an item is emitted by either of two Observables, and the latest item emitted by each Observable is combined via a specified function and the resulting items are emitted based on the results of this function. The [sample code](https://gist.github.com/anitaa1990/3413a914cc200aab1147f4d4fb3b95d2#file-combinelatestobservable-java) demonstrates the use of ```combineLatest()``` operator. Let’s say there are 2 Observables each emitting values after an interval of 100 ms and 150 ms respectively. The ```combineLatest()``` operator combines both the observables and emits the result at each particular intervals.

* [Join](http://reactivex.io/documentation/operators/join.html) — Whenever two items (each one for one source) are overlapped, they will be paired and sent to the resultSelector which computes and returns them. The join() operator takes the following items:
  * <b>right</b> — the second Observable to join items from.
  * <b>leftDurationSelector</b> — a function to select a duration for each item emitted by the source Observable, used to determine overlap.
  * <b>rightDurationSelector</b> — a function to select a duration for each item emitted by the right Observable, used to determine overlap.
  * <b>resultSelector</b> — a function that computes an item to be emitted by the resulting Observable for any two overlapping items emitted by the two Observables.
  * The [sample code](https://gist.github.com/anitaa1990/fc436dc276c15c9552b3df87f76f856e#file-joinobservable-java) demonstrates the use of ```join()``` operator. In the below sample, we create two Observables: left & right which emits a value every 100 milliseconds. We use the ```join()``` operator to join the left Observable to the right Observable. The two integers emitted from both the Observables are added and the result is printed.
  
* [Merge](http://reactivex.io/documentation/operators/merge.html) — This operator combines multiple Observables into one by merging their emissions i.e. merges multiple Observables into a single Observable but it won’t maintain the sequential execution. ```merge()``` operator doesn’t wait for data from observable 1 to complete. It emits data from both the observable simultaneously as soon as the data becomes available to emit. [Sample Code](https://gist.github.com/anitaa1990/bf61c995f4198e6fcaa5218ecf712aba#file-mergeobservable-java)

* [Concat](http://reactivex.io/documentation/operators/concat.html) — This operator combines the output of two or more Observables into a single Observable, without interleaving them i.e. the first Observables completes its emission before the second starts and so forth if there are more observables. [Sample Code](https://gist.github.com/anitaa1990/f19512cee4dbfee0ebcc2d5da0ffb3c9#file-concatobservable-java)

<b>Note:</b> The difference between ```merge()``` and ```concat()``` is that ```merge()``` interweaves output while ```concat()``` waits for earlier emissions to complete before processing new emissions.

* [Zip](http://reactivex.io/documentation/operators/zip.html) — This operator combines the emissions of multiple Observables together via a specified function and emit single items for each combination based on the results of this function. [Sample Code](https://gist.github.com/anitaa1990/2a1a7309b0a050ebd307bac3913428db#file-zipobservable-java)

* [SwitchOnNext](http://reactivex.io/documentation/operators/switch.html) — This operator emits items from the first Observable until the second Observable start emitting. Then, it unsubscribes from the first Observable and start emitting items from the second one. [Sample Code](https://gist.github.com/anitaa1990/f4fcece4b0159e387be722e14836b903#file-switchonnextobservable-java)

### Utility Operators
* [Delay](http://reactivex.io/documentation/operators/delay.html) — This operator shifts the emissions from an Observable forward in time by a particular amount. i.e. modifies its source Observable by pausing for a particular increment of time before emitting each of the items from the Obervable. The [sample code](https://gist.github.com/anitaa1990/1d9c440c25d05f175c9dbc90aeb3a955#file-delayobservable-java) emits all the Observable items after a delay of 2 seconds.

* [Do](http://reactivex.io/documentation/operators/do.html) — This operator registers an action to take upon a variety of Observable lifecycle events.
  * The ```doOnNext()``` operator modifies the Observable source so that it invokes an action when the ```onNext()``` is called.
  * The ```doOnCompleted()``` operator registers an action so that it invokes an action when the ```onComplete()``` is called.
  * The ```doOnEach()``` operator modifies the Observable source so that it notifies an Observer for each item and establishes a callback that will be called each time an item is emitted.
  * The ```doOnSubscribe()``` operator registers an action which is called whenever an Observer subscribes to the resulting Observable.
  * [Sample Code](https://gist.github.com/anitaa1990/3c232c91dd673354bd74bea1303274de#file-doobservable-java)
  
* [Materialize/Dematerialize](http://reactivex.io/documentation/operators/materialize-dematerialize.html) — This operator represents both the items emitted and the notification sent as emitted items, or vice versa. What ```materialize()``` does is basically wrap the observed object types into an observable Notification object on which we can check whether the ```onNext()```, ```onError()``` and/or ```onComplete()``` methods are called. ```dematerialize()```, as you might guess, reverses the effect. The [sample code](https://gist.github.com/anitaa1990/e405b024c20a54bc55d2e3f2244c663e#file-materializeobservable-java) demonstrates the use of ```materialize()``` operator. From the ```materialize()``` operator, we can get the notification object. Using this object, we can check if the emitted item is: ```isOnNext()``` or ```isOnError()``` or ```isOnComplete()```. Here we can basically fetch items that are successful and omit items that resulted in error.

* [ObserveOn](http://reactivex.io/documentation/operators/observeon.html) — This operator specifies the scheduler on which an observer will observe this Observable. By default, an Observable along with the operator chain will operate on the same thread on which its Subscribe method is called. The ```observeOn()``` operator specifies a different Scheduler that the Observable will use for sending notifications to Observers. [Sample Code](https://gist.github.com/anitaa1990/0c8904c35e50a1c42e70186df02c6149#file-observeonobservable-java)

* [SubscribeOn](http://reactivex.io/documentation/operators/subscribeon.html) — This operator tells the source Observable which thread to use for emitting items to the Observer. [Sample Code](https://gist.github.com/anitaa1990/5679f744e43a2ddda36cf7fff7a830f5#file-subscribeonobservable-java)

* [TimeInterval](http://reactivex.io/documentation/operators/timeinterval.html) — This operator converts an Observable that emits items into one that emits indications of the amount of time elapsed between those emissions. i.e. if we are more interested in how much time has passed since the last item, rather than the absolute moment in time when the items were emitted, we can use the timeInterval() method. [Sample Code](https://gist.github.com/anitaa1990/e6e50132c6e1116e9c0c2fffe5a4d18e#file-timeinterval-java)

* [Timeout](http://reactivex.io/documentation/operators/timeout.html) — This operator mirrors the source Observable, but issues an error notification if a particular period of time elapses without any emitted items. The [sample code](https://gist.github.com/anitaa1990/b154fc63e82c58181fe83c409dc457a7#file-timeoutobservable-java) delays the emission of items by 1 second. But we have added a timeout that throws an exception if there is no emission within 500ms. Hence the below code will throw an error.

* [Timestamp](http://reactivex.io/documentation/operators/timestamp.html) — This operator attach a timestamp to each item emitted by an Observable. It transforms the items into the ```Timestamped<T>``` type, which contains the original items, along with a timestamp for when the event was emitted. [Sample Code](https://gist.github.com/anitaa1990/a911f270e287f2e0c1d5342456f63fa7#file-timestampobservable-java)

* [Using](http://reactivex.io/documentation/operators/using.html) — This operator creates a disposable resource that has the same lifespan as the Observable. The ```using()``` operator is a way you can instruct an Observable to create a resource that exists only during the lifespan of the Observable and is disposed of when the Observable terminates. [Sample Code](https://gist.github.com/anitaa1990/72bf6d9270fbd75cde2e9f55cd4b055c#file-usingobservable-java)


### Conditional and Boolean Operators
* [All](http://reactivex.io/documentation/operators/all.html) — This operator determines whether all items emitted by an Observable meet some criteria. [Sample Code](https://gist.github.com/anitaa1990/13d3de8b0b0082d691d903e43f5a4dd5#file-allobservable-java)

* [Amb](http://reactivex.io/documentation/operators/amb.html) — When you pass a number of source Observables to ```amb()```, it will pass through the emissions and notifications of exactly one of these Observables: the first one that sends a notification to Amb, either by emitting an item or sending an ```onError``` or ```onCompleted``` notification. Amb will ignore and discard the emissions and notifications of all of the other source Observables. The [sample code](https://gist.github.com/anitaa1990/963675daa92cfa3534ee32bf39bda4d0#file-ambobservable-java) demonstrates the use of ```amb()``` operator. In the below example, the ```observable2``` gets executed because it is the first to emit its first item. ```observable1``` is ignored.

* [Contains](http://reactivex.io/documentation/operators/contains.html) — This operator determines whether an Observable emits a particular item or not. [Sample Code](https://gist.github.com/anitaa1990/d95bfd5b5e6911c67384d233f9277251#file-containsobservable-java)

* [DefaultIfEmpty](http://reactivex.io/documentation/operators/defaultifempty.html) — This operator emits items from the source Observable, or a default item if the source Observable emits nothing. The [sample code](https://gist.github.com/anitaa1990/e49aadf468915d7361e91c531b4dbbaf#file-defaultifemptyobservable-java) generates a random number and if the number is an even number, it is emitted, otherwise the Observable completes its emission and a default value we have specified (-10 in this case), is emitted.

* [SequenceEqual](http://reactivex.io/documentation/operators/sequenceequal.html) — This operator determines whether two Observables emit the same sequence of items. [Sample Code](https://gist.github.com/anitaa1990/446350dea0e6c19a1e0fc461ddeed2d8#file-sequenceequalobservable-java)

* [SkipUntil](http://reactivex.io/documentation/operators/skipuntil.html) — This operator discards items emitted by an Observable until a second Observable emits an item. In the [sample code](https://gist.github.com/anitaa1990/2e8a40a7ebdebe8994a68097331c8e9c#file-skipuntilobservable-java), after emitting 3, 4, 5, the second Observable starts emitting items after three seconds.

* [SkipWhile](http://reactivex.io/documentation/operators/skipwhile.html) — This operator will discard items emitted by an Observable until a specified condition becomes false. In the [sample code](https://gist.github.com/anitaa1990/72de811f44a65030adf7c26cef4772bc#file-skipwhileobservable-java), we have specified a list of integers from 0 to 6. All items are ignored until the given condition fails.

* [TakeUntil](http://reactivex.io/documentation/operators/takeuntil.html) — This operator discards items emitted by an Observable until after a second Observable emits an item or terminates. It is the exact opposite of ```skipUntil```. [Sample Code](https://gist.github.com/anitaa1990/6deea32baa84e35da513a21f47a31c71#file-takeuntilobservable-java)

* [TakeWhile](http://reactivex.io/documentation/operators/takewhile.html) — Thos operator will discard items emitted by an Observable after a specified condition becomes false. In the [sample code](https://gist.github.com/anitaa1990/ec976c29b5b2535112b47a6283554275#file-takewhileobservable-java), we have specified a list of integers from 0 to 6. All items are emitted until the given conditions fails.

### Mathematical and Aggregate Operators
Mathematical Observables require extra dependency. Add the following dependency inside the build.gradle file and sync gradle.
```
implementation 'com.github.akarnokd:rxjava2-extensions:0.20.0'
```
 * [Average](http://reactivex.io/documentation/operators/average.html) — This operator calculates the average of the items emitted by an Observable and emits this average. [Sample Code](https://gist.github.com/anitaa1990/ace180960433595fa6193d9d8b70e760#file-averageobservable-java)
 
 * [Count](http://reactivex.io/documentation/operators/count.html) — This operator counts the number of items emitted by the source Observable and emit only this value. [Sample Code](https://gist.github.com/anitaa1990/0c324928bd807030979ded1efbee0897#file-countobservable-java)
 
 * [Max](http://reactivex.io/documentation/operators/max.html) — This operator determines the maximum-valued item emitted by an Observable sequence and emits that item. [Sample Code](https://gist.github.com/anitaa1990/bebb610c0a541a1d727808ad90814970#file-maxobservable-java)
 
 * [Min](http://reactivex.io/documentation/operators/min.html) — This operator determines the minimum-valued item emitted by an Observable sequence and emits that item. [Sample Code](https://gist.github.com/anitaa1990/ee05639b630b95fa2994a9e23c9513ba#file-minobservable-java)
 
 * [Reduce](http://reactivex.io/documentation/operators/reduce.html) — This operator applies a function to each item emitted by an Observable, sequentially, and emit the final value. First it applies a function to first item, takes the result and feeds back to same function on second item. This process continuous until the last emission. Once all the items are over, it emits the final result. [Sample Code](https://gist.github.com/anitaa1990/1e34a05d936d5c57d2e75e2c7b5930a6#file-reduceobservable-java)
 
 * [Sum](http://reactivex.io/documentation/operators/sum.html) — This operator calculates the sum of numbers emitted by an Observable and emits this sum. [Sample Code](https://gist.github.com/anitaa1990/1e071e5c2d733d668ffd994ca47b476e#file-sumobservable-java)
 

### Different Types of Observables
* [Single](http://reactivex.io/documentation/single.html) — Single is an Observable that always emit only one value or throws an error. A typical use case of Single observable would be when we make a network call in Android and receive a response. The [sample code](https://gist.github.com/anitaa1990/3578e2d48058d1c73d19057fa327dbfa#file-singleobservable-java) always emits a Single ```user``` object. We use a Single Observable and a Single Observer. The Single Observer always emits only once so there is no ```onNext()``` .

* [Maybe](http://reactivex.io/RxJava/javadoc/io/reactivex/Maybe.html) — Maybe is an Observable that may or may not emit a value. For example, we would like to know if a particular user exists in our db. The user may or may not exist. The [sample code](https://gist.github.com/anitaa1990/6d5dd31538e1f16e259d5b685454bb3a#file-maybeobservable-java) mimics a scenario where a ```user``` object is emitted. We use a Maybe Observable and a Maybe Observer. Again we are using the same scenario as above: creating a new ```User```.

* [Completable](http://reactivex.io/RxJava/2.x/javadoc/io/reactivex/Completable.html) — Completable does not emit any data, but rather is focused on the status of execution — whether successful or failure. The [sample code]() mimics a scenario where an existing ```User``` object is updated. Since no data is emitted in Completable, there is no ```onNext()``` or ```onSuccess()``` . This scenario can be used in cases where PUT api is called and we need to update an existing object to the backend. [Sample Code](https://gist.github.com/anitaa1990/07f27f8b0a164355255237ea0120bc0c#file-completableobservable-java)

* [Flowable](http://reactivex.io/RxJava/javadoc/io/reactivex/Flowable.html) — Flowable is typically used when an Observable is emitting huge amounts of data but the Observer is not able to handle this data emission. This is known as <b>Back Pressure</b>. The [sample code](https://gist.github.com/anitaa1990/5201f473c64853a28ac7b77cf044eb21#file-flowableobservable-java) provides a range of integers from 10 to 1000 and uses the ```reduce()``` operator to add the sum of the integers and emit the final sum value.

You can checkout a lot more about Flowable operators from [here](https://www.baeldung.com/rxjava-2-flowable).


### Subjects and different Types of Subjects
 * A [Subject](http://reactivex.io/documentation/subject.html) extends an Observable and implements Observer at the same time. It acts as an Observable to clients and registers to multiple events taking place in the app. It acts as an Observer by broadcasting the event to multiple subscribers.
 * <b>Characteristics of Subjects</b>:
    * Subjects can act as both an Observer and an Observable. In the following example, we create an Observable which emits integers from 1 to 5. We create a subject, and use it to observe the changes to the Observable(In this scenario, the Subject is acting as an Observer). We will have two Observers to observe the changes in the Subject (In this scenario, the Subject is acting as an Observable). [Example](https://gist.github.com/anitaa1990/6ff85e801e45ff262490f0a44578735a#file-subjectexample-java)
    * Subjects can multicast items to multiple child subscribers. Multicasting makes it possible to run expensive operations once and emit the results to multiple subscribers. This prevents doing duplicate operations for multiple subscribers.  In the following example, we create an Observable which emits integers from 1 to 5. Each integer is squared by itself using the map() operator before it is emitted. We will have two Observers to observe the Observable. [Example without Subjects](https://gist.github.com/anitaa1990/dc168b63a0a9839cf240e911a8bee0e9#file-multiplesubscriberswithoutsubjectsexample-java) [Example using Subjects](https://gist.github.com/anitaa1990/7aef38776789bdccbcee179faf55ab04#file-multiplesubscriberswithsubjectsexample-java)
    * Subjects are considered as HOT Observables. A HOT Observable, such as Subjects, emits items only once regardless of number of subscribers and its subscribers receive items only from the point of their subscription. Subjects convert cold observable into hot observable. Example scenario: In the following example, we create a Subject which emits an integer from 1 to 4. We will add two Observers to observe the emission. [Example](https://gist.github.com/anitaa1990/244107a2402bbf8201251ad968d398b8#file-hotobservableexample-java). You will notice from the above output in the example that,
         * Even though the Subject emits the integer value ‘0’, it is not printed. This is because there are no subscribers that are listening to the emission.
         * Observer 2 only prints values ‘3’ and ‘4’. This is because the second Observer only subscribed to the Subject after it emitted values 0, 1 and 2.
 * <b>Types of Subjects</b>:
   * [PublishSubject](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/PublishSubject.html): PublishSubject emits all the items at the point of subscription. This is the most basic form of Subject. [Example](https://gist.github.com/anitaa1990/3b41656f7f364293ae4a66b4e17d099b#file-publishsubjectexample-java)
   * [BehaviorSubject](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/BehaviorSubject.html): BehaviorSubject emits the most recent item at the time of their subscription and all items after that. [Example](https://gist.github.com/anitaa1990/beefc2f31d9d18067e38e536c7b74b02#file-behaviorsubjectexample-java). 
      * Difference between PublishSubject and BehaviorSubject is that PublishSubject prints all values after subscription and BehaviorSubject prints the last emitted value before subscription and all the values after subscription.
   * [ReplaySubject](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/ReplaySubject.html): ReplaySubject emits all the items of the Observable, regardless of when the subscriber subscribes. [Example](https://gist.github.com/anitaa1990/ee9fad35f2e27c7bdf36d32bfda9a47e#file-replaysubjectexample-java).
   * [AsyncSubject](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/AsyncSubject.html): AsyncSubject emits only the last value of the Observable and this only happens after the Observable completes. [Example](https://gist.github.com/anitaa1990/9342b723c44e54616a257ff2a06e6125#file-asyncsubject-java)
   * UnicastSubject: UnicastSubject allows only a single subscriber and it emits all the items regardless of the time of subscription.  [Example](https://gist.github.com/anitaa1990/14269d7ecaa86ea3bd8193de7f6ae048#file-unicastsubjectexample-java).
         














     
