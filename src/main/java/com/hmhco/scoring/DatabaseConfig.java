package com.hmhco.scoring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jayachandranj on 9/20/17.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class DatabaseConfig {

    private String  reportingUrl;
    private String  reportingUsername;
    private String  reportingPassword;
    private String  scoringUrl;
    private String  scoringUsername;
    private String  scoringPassword;

    public String getReportingUrl() {
        return reportingUrl;
    }

    public void setReportingUrl(String reportingUrl) {
        this.reportingUrl = reportingUrl;
    }

    public String getReportingUsername() {
        return reportingUsername;
    }

    public void setReportingUsername(String reportingUsername) {
        this.reportingUsername = reportingUsername;
    }

    public String getReportingPassword() {
        return reportingPassword;
    }

    public void setReportingPassword(String reportingPassword) {
        this.reportingPassword = reportingPassword;
    }

    public String getScoringUrl() {
        return scoringUrl;
    }

    public void setScoringUrl(String scoringUrl) {
        this.scoringUrl = scoringUrl;
    }

    public String getScoringUsername() {
        return scoringUsername;
    }

    public void setScoringUsername(String scoringUsername) {
        this.scoringUsername = scoringUsername;
    }

    public String getScoringPassword() {
        return scoringPassword;
    }

    public void setScoringPassword(String scoringPassword) {
        this.scoringPassword = scoringPassword;
    }
}
