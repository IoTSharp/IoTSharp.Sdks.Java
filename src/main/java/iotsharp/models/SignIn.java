package iotsharp.models;

public class SignIn {
  private   boolean succeeded;
    private   boolean isLockedOut;
    private   boolean isNotAllowed;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public boolean isLockedOut() {
        return isLockedOut;
    }

    public void setLockedOut(boolean lockedOut) {
        isLockedOut = lockedOut;
    }

    public boolean isNotAllowed() {
        return isNotAllowed;
    }

    public void setNotAllowed(boolean notAllowed) {
        isNotAllowed = notAllowed;
    }

    public boolean isRequiresTwoFactor() {
        return requiresTwoFactor;
    }

    public void setRequiresTwoFactor(boolean requiresTwoFactor) {
        this.requiresTwoFactor = requiresTwoFactor;
    }

    private   boolean requiresTwoFactor;
}
