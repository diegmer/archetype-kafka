package com.adidas.kafka.influxdb;

import cucumber.api.Scenario;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Connection with InfluxDB database.
 */
public class InfluxDBIntegration {

    private static InfluxDBIntegration instance;
    private InfluxDB influxDB;

    /**
     * Class constructor. Sets a connection to InfluxDB
     * */
    private InfluxDBIntegration() {
        influxDB = InfluxDBFactory.connect("http://localhost:8086", "mp", "mp@123)");
    }

    /**
     * Singleton pattern to prevent several instances of this class
     **/
    public static InfluxDBIntegration getInstance() {
        if (instance == null) instance = new InfluxDBIntegration();
        return instance;
    }

    /**
     * Method to write on InfluxDB
     * @param scenario Writes scenario properties on Influx
     * @param startTime Used to start measurement of execution time
     * @param endTime Used to end measurement of execution time
     * */
    public void writeInfluxDB(Scenario scenario, Calendar startTime, Calendar endTime) {


        String testScope= "examples";
        String environment = "local";

        String dbName = "examples_be_performance";
        BatchPoints batchPoints;
        batchPoints = BatchPoints
                .database(dbName)
                .tag("testScope", testScope)
                .tag("environment", environment)
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();

        Point point1 = Point.measurement(testScope)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("test_case", scenario.getId())
                .addField("description", scenario.getName())
                .addField("execution_time", ((double) endTime.getTimeInMillis() - (double) startTime.getTimeInMillis()) / 1000)
                .addField("status", scenario.getStatus().toString())
                .addField("environment", environment)
                .build();
        batchPoints.point(point1);
        try {
            influxDB.write(batchPoints);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
