@Mapper
public interface SettingsMapper {
    SettingsMapper INSTANCE = Mappers.getMapper(SettingsMapper.class);

    @Mapping(target = "id", ignore = true)
    Settings copy(Settings settings);
}

Settings copy = SettingsMapper.INSTANCE.copy(defaultSettings);
