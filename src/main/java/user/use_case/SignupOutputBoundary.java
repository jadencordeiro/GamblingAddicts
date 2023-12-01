package user.use_case;


public interface SignupOutputBoundary {

      void prepareSuccessView(SignupOutputData user);

    //public abstract void prepareSuccessView(SignupOutputData response);

    //public abstract void prepareSuccessView(SignupOutputData response);

    void prepareFailView(String error);
}
