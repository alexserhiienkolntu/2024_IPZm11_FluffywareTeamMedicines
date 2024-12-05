package ua.edu.lntu.fluffywareteam.medicines.stack

class LoginFormStack {

    var username: String = ""
    var password: String = ""

    fun set(username: String? = null, password: String? = null) {
        if (username != null) {
            this.username = username
        }
        if (password != null) {
            this.password = password
        }
    }

    fun clear() {
        username = ""
        password = ""
    }
}