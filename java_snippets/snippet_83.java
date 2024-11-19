final RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(
    Duration.ofMillis(5000L)).setReadTimeout(Duration.ofMillis(5000L)).build();

final String response = restTemplate.exchange("https://ws-public.interpol.int/notices/v1/red",
    HttpMethod.GET, entity, String.class).getBody();
