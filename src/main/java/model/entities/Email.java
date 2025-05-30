package model.entities;

import java.util.Objects;

public class Email {

	private String email;

	public Email() {
	}

	public Email(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "Email [email=" + email + "]";
	}
}
