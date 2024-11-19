@ExceptionHandler(Exception.class)
public String handleSomeException() {
    return "error/errorPage";  // updated path
}
