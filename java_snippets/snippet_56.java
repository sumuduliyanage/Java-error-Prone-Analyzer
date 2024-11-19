  @Bean (name = TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME)
  public ThreadPoolTaskExecutor getApplicationTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setMaxPoolSize(100);
    executor.setCorePoolSize(100);
    executor.setBeanName("ws-pool-exec");
    executor.initialize();
    return executor;
  }
