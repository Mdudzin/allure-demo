package Utils;

public class ConfigurationReader extends FileReader {
    private String configurationLocation;

    private String hubUrl;
    private String baseUrl;
    private String browser;
    private boolean isRemote;

    public ConfigurationReader(String configurationLocation) {
        super(configurationLocation);
        this.configurationLocation = configurationLocation;
    }


    void loadData() {
        hubUrl = properties.getProperty("hubUrl");
        baseUrl = properties.getProperty("baseUrl");
        browser = properties.getProperty("browser");
        isRemote = Boolean.parseBoolean(properties.getProperty("isRemote"));
    }

    public String getBrowser() {
        return browser;
    }

    public String getHubUrl() {
        return hubUrl;
    }

    public String getConfigurationLocation() {
        return configurationLocation;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public boolean isRemote(){ return isRemote; }
}
