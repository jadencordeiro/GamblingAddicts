package user.use_case;


public abstract class SignupOutputBoundary {

    protected abstract void prepareSuccessView(SignupOutputData user);

    //public abstract void prepareSuccessView(SignupOutputData response);

    //public abstract void prepareSuccessView(SignupOutputData response);

    public abstract void prepareFailView(String error);
}
