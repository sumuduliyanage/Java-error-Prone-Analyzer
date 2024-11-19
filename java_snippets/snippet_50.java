    String errMsg = "Cannot find %s in school %s";

    Validator.validate((t) -> Objects.isNull(t),
            school.getTeacher(),
            String.format(errMsg, "teacher", school.getName()));

    Validator.validate((t) -> Objects.isNull(t),
            teacher.getStudent(),
            String.format(errMsg, "student", school.getName()));
