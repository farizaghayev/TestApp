# TestApp
Native android app for TestApp


TestApp project it is divided into 3 layers like this:

**Domain Layer**

* Contains business model
* Contains business RULEs
* Repository interface adapt

**Data Layer**

* Implementation Repository
* Executor API data
* Storage data to local: Share preferences, database, external storage
* Mapper data model to domain model
* Contains data service, third-party data service


**Presentation Layer**

* View (Activity/Fragment/Layout) Adapt data to view
* Validate/Submit data input from view via UseCase

**Data-flow**

![image](https://user-images.githubusercontent.com/18733219/217038164-91317d96-385a-4147-82b6-1f3d9c7ec814.png)

**Work-flow**

![image](https://user-images.githubusercontent.com/18733219/217038252-bfda8369-5c2f-4979-b504-0eb3a76c58e4.png)


**Tech stack & Open-source libraries**

* **Kotlin** based, Coroutines + Flow for asynchronous.
* **Hilt** for dependency injection.
* **LiveData** notify domain layer data to views.
* **ViewModel** UI-related data holder, lifecycle aware.
* **Room Persistence** construct a database using the abstract layer
* **MVVM** Architecture (View - DataBinding - ViewModel - Model)
* **Glide** loading images
* **Retrofit2 & OkHttp3** construct the REST APIs and paging network data.
* **Gson** JSON representation
* **Material** -Components Material design components
* **ProGuard** - disguise the code so that it is difficult to read by others after reverse engineering