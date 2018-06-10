package com.example.demo.command;

/**
 * Created by wdg on 2018/6/8.
 */
public class UpdatePwdCommand {
    String oldpwd;
    String newpwd;
    String confirmpwd;

    public void setConfirmpwd(String confirmpwd) {
        this.confirmpwd = confirmpwd;
    }

    public String getConfirmpwd() {
        return confirmpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }

    public String getOldpwd() {
        return oldpwd;
    }
}
