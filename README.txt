Fork of jadira with the java version fix from https://github.com/JadiraOrg/jadira/pull/66 applied to each release.

I removed the references to the toolchain plugin from all the poms, as that was causing errors building on jitpack.
This means that each branch will be built with the build environment's default jdk (currently Java 8).

To add to your project, replace the usertype.spi dependency with this one. For instance, I have:

```gradle
repositories {
	/*...*/

	maven { url "https://jitpack.io" }
}

dependencies {
	/*...*/

    compile group: 'org.jadira.usertype', name: 'usertype.core', version: ver.jadira, {
        exclude module: 'usertype.spi'
    }
    compile "com.github.bgorven.jadira:usertype.spi:${ver.jadira}-jdk9-SNAPSHOT"
}
```

Usertype
================================================

User Types and classes for use with Hibernate. Includes user types for use with the javax.time 
classes included in the early review class of JSR 310. Where possible these types are compatible 
with the equivalent Joda Time - Hibernate user types. 

User Types includes user types for Joda Time. These are designed for interoperability
wherever possible with the provided JSR 310 user types. These can be used as an alternative to Joda
Time Contrib's persistent types. The motivation for creating these types is the original types are
affected by an issue whereby the written time is offset by the user's offset from the database zone.

User Type includes other user types - for enums, JDK types and Libphonenum.

Bindings
================================================

Bindings is a library supporting a variety of methods for defining bindings between classes and their String representation

Cloning
================================================

Cloning provides fast deep cloning capabilities for almost any Java object.

CDT
================================================

CDT provides common data types for commonly used types such as ISO Country Codes, Phone Numbers and other types.

Quant
================================================

Quant provides classes implementing common quantitative functions.

Scanner
================================================

Scanner provides capability for scanning the classpath without loading classes.

Thanks
================================================

YourKit is kindly supporting open source projects with its full-featured Java Profiler. 
YourKit, LLC is the creator of innovative and intelligent tools for profiling Java and .NET 
applications. Take a look at YourKit's leading software products: 
YourKit Java Profiler http://www.yourkit.com/java/profiler/index.jsp and 
YourKit .NET Profiler http://www.yourkit.com/.net/profiler/index.jsp.
