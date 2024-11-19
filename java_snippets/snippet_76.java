public static void main(String[] args) {
    try {
        String dataToCompress = "this is the test string using ZOS";
        try (OutputStream os = new FileOutputStream("output.zip");
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream(dataToCompress.length());
            ZipOutputStream zipStream = new ZipOutputStream(byteStream)) {
            String filePath = "test.txt";
            File file = new File(filePath);
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zipStream.putNextEntry(zipEntry);
            zipStream.write(dataToCompress.getBytes());
            // Close Statements
            zipStream.close();
            byteStream.close();
            byte[] compressedData = byteStream.toByteArray();
            os.write(compressedData);
        }
        System.out.println("File zipped successfully!");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
