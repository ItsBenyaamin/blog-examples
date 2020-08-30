## Single source of truth example
In this simple project we will learn how to implement SSOT principal in the right way in Android.

## how It works?
**First step**: we must observe the cache(Room) list and if data doesn't exist in cache, we send a request to server for newer information.

**second step**:  update the cache with new information and refresh the cache list which we already observed. then UI will automatically updated with the new information.

![SSOT example](https://benyaamin.com/blog/wp-content/uploads/2020/08/ssot_diagram-768x705.png "SSOT example")

## libraries
- **Retrofit**
- **Room**
- **Lifecycle Components(LiveData, ViewModel)** 
