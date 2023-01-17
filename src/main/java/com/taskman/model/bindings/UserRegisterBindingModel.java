package com.taskman.model.bindings;

import javax.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class UserRegisterBindingModel {
        private String email;
        private String fullName;
        private String password;
        private String confirmPassword;

        public UserRegisterBindingModel() {
        }

        @Email(message = "Invalid e-mail format!")
        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        @Length(min=5, message = "Full name is too short. Minimum length is 5 letters!")
        public String getFullName() {
                return fullName;
        }

        public void setFullName(String fullName) {
                this.fullName = fullName;
        }

        @Length(min=5, message = "Password is too short. Minimum length is 5 letters!")
        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getConfirmPassword() {
                return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
                this.confirmPassword = confirmPassword;
        }
}
