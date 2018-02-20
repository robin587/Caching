# Caching
Caching Layer for standalone java application

Use jdk1.8 and above

Installation
Run build.xml which will generate myCache-v.jar.Include the jar in your build path.

Usage
CacheMgr.initializeCache() - it initializes all the persistent data to second level cache

DataToBeCachedUtil - maintains the Objects to be cached

CacheMgr.loadDataRead(String tableName, String key) - Read the object from the cache.If not present will read from the database

CacheMgr.loadDataWrite(String tableName, String key) - This method should be used when the client needs a modifiable reference

Testing
Run JUnit Test Suite - AllTests

