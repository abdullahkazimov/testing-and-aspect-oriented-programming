package abdkzmv.wm2.assignment3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class MovieValidationException extends RuntimeException {

    private BindingResult bindingResult;

    public MovieValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }


    public BindingResult getBindingResult() {
        return bindingResult;
    }
}