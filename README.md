# Auth Android
> Auth Android provides an easy to use wrapper for OAuth with major 3rd party providers. Useful for google , facebook & twitter authentications.

- Using Auth Android , authentication to your favourite services can be done in only 2 lines of code rather than 50 + lines with many callbacks and condition checks.

- Auth Android handles all the heavy work in its library so user can be free from juggling with so many lines of code while both implementing & debugging.

#### Current Features (v0.2.0)
- Google authentication
	- basic authentication
	- web application client id authentication

- The library is written in kotlin using lambda functions so it may/may not be used with java.

#### Implementation
- Follow this [link](https://developers.google.com/identity/sign-in/android/start-integrating)  only to configure play services and if needed the web client id.
- In your gradle (app) config file add
~~~
repositories {
	maven {
		 url "https://dl.bintray.com/dawnimpulse/android"
	 }
}
~~~
(will be soon directly available on **jcenter**)

~~~
	implementation 'com.dawnimpulse:auth:0.2.0'
~~~

#### Usage

- Basic authentication
~~~
var authGoogle = AuthGoogle(context);

// for signin
authGoogle.signIn({ error,account ->
	//error (string)-> "signInResult:failed code=#statusCode" , null for success
	//account (GoogleSignInAccount) , null for failure
})
~~~
- Web Client Id authentication
~~~
// for signin
authGoogle.signIn("#client-id",{ error,account ->
	//

})
~~~
- Sign Out
~~~
authGoogle.signOut({ error,resp ->
 //error (string) - task failed (will be logged on logcat with tag AuthAndroid)
 //resp (boolean) - true only
})
~~~

#### Releases
- v0.2.0
	- Google auth basic
	- Google auth with web client id

#### Upcoming
- v0.3.0
	- Complete google authentication with support for other permissions except basic account info.

> Pull requests are always welcomed (kindly sign commits with GPG keys. **THANKS**)
#### Contact
-   Twitter -  [@dawnimpulse](https://twitter.com/dawnimpulse)

#### License
~~~~
ISC Licence

Copyright 2018 Saksham (DawnImpulse)

Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
provided that the above copyright notice and this permission notice appear in all copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE
OR PERFORMANCE OF THIS SOFTWARE.
~~~~