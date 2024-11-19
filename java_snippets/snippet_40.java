LinkedList < Integer > integers = new LinkedList <> ( List.of ( 1 , 2 , 3 ) );
IntStream.rangeClosed ( 4 , 6 ).forEach ( integers :: add );

System.out.println ( "integers.toString() = " + integers );
System.out.println ( integers.getClass ( ).getCanonicalName ( ) );
