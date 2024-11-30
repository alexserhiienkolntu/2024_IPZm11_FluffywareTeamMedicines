package ua.edu.lntu.fluffywareteam.medicines.stack

class LoginFormStack {

    var login: String = ""
    var password: String = ""

    fun set(login: String? = null, password: String? = null) {
        if (login != null) {
            this.login = login
        }
        if (password != null) {
            this.password = password
        }
    }

    fun clear() {
        login = ""
        password = ""
    }
}