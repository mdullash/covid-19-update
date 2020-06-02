
package com.example.covid_19update.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WorldBd implements Serializable {

    @SerializedName("updated")
    @Expose
    private long updated;
    @SerializedName("cases")
    @Expose
    private long cases;
    @SerializedName("todayCases")
    @Expose
    private long todayCases;
    @SerializedName("deaths")
    @Expose
    private long deaths;
    @SerializedName("todayDeaths")
    @Expose
    private long todayDeaths;
    @SerializedName("recovered")
    @Expose
    private long recovered;
    @SerializedName("active")
    @Expose
    private long active;
    @SerializedName("critical")
    @Expose
    private long critical;
    @SerializedName("casesPerOneMillion")
    @Expose
    private long casesPerOneMillion;
    @SerializedName("deathsPerOneMillion")
    @Expose
    private double deathsPerOneMillion;
    @SerializedName("tests")
    @Expose
    private long tests;
    @SerializedName("testsPerOneMillion")
    @Expose
    private double testsPerOneMillion;
    @SerializedName("population")
    @Expose
    private long population;
    @SerializedName("activePerOneMillion")
    @Expose
    private double activePerOneMillion;
    @SerializedName("recoveredPerOneMillion")
    @Expose
    private double recoveredPerOneMillion;
    @SerializedName("criticalPerOneMillion")
    @Expose
    private double criticalPerOneMillion;
    @SerializedName("affectedCountries")
    @Expose
    private long affectedCountries;

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Long getCases() {
        return cases;
    }

    public void setCases(Long cases) {
        this.cases = cases;
    }

    public Long getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(Long todayCases) {
        this.todayCases = todayCases;
    }

    public Long getDeaths() {
        return deaths;
    }

    public void setDeaths(Long deaths) {
        this.deaths = deaths;
    }

    public Long getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(Long todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public Long getRecovered() {
        return recovered;
    }

    public void setRecovered(Long recovered) {
        this.recovered = recovered;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public Long getCritical() {
        return critical;
    }

    public void setCritical(Long critical) {
        this.critical = critical;
    }

    public Long getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public void setCasesPerOneMillion(Long casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public Double getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public void setDeathsPerOneMillion(Double deathsPerOneMillion) {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public Long getTests() {
        return tests;
    }

    public void setTests(Long tests) {
        this.tests = tests;
    }

    public Double getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public void setTestsPerOneMillion(Double testsPerOneMillion) {
        this.testsPerOneMillion = testsPerOneMillion;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Double getActivePerOneMillion() {
        return activePerOneMillion;
    }

    public void setActivePerOneMillion(Double activePerOneMillion) {
        this.activePerOneMillion = activePerOneMillion;
    }

    public Double getRecoveredPerOneMillion() {
        return recoveredPerOneMillion;
    }

    public void setRecoveredPerOneMillion(Double recoveredPerOneMillion) {
        this.recoveredPerOneMillion = recoveredPerOneMillion;
    }

    public Double getCriticalPerOneMillion() {
        return criticalPerOneMillion;
    }

    public void setCriticalPerOneMillion(Double criticalPerOneMillion) {
        this.criticalPerOneMillion = criticalPerOneMillion;
    }

    public Long getAffectedCountries() {
        return affectedCountries;
    }

    public void setAffectedCountries(Long affectedCountries) {
        this.affectedCountries = affectedCountries;
    }
}