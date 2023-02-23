// Read more: https://kotlinlang.org/docs/inline-classes.html

// Note: it basically doesn't work with Java, unfortunately (for now)

class User(val userName: String, val password: String)

var user = User("myPassword", "myName") // obviously wrong, but compiles

class UserWithInlineClasses(val userName: UserName, val password: Password)

var userWithInlineClasses = UserWithInlineClasses(UserName("myName"), Password("password"))

@JvmInline
value class UserName(val userName: String)

@JvmInline
value class Password(val password: String)
