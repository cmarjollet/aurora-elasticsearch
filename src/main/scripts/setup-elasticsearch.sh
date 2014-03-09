#!/bin/sh

# Create aurora_test index
curl -XPUT 'http://localhost:9200/aurora_test/' -d '{
    "settings" : {
        "number_of_shards" : 1,
        "number_of_replicas" : 0
    }
}'

# Create mapping for 'power' document
curl -XPUT 'http://localhost:9200/aurora_test/power/_mapping' -d '
{
    "power" : {
        "properties" : {
            "satellite" : {"type" : "string", "store" : true },
            "hemisphere" : {"type" : "string", "store" : true },
            "date" : {"type" : "date", "store" : true },
            "power" : {"type" : "float", "store" : true },
            "activityLevel" : {"type" : "integer", "store" : true },
            "normalizingFactor" : {"type" : "float", "store" : true }
        },
        "_timestamp" : {
            "enabled" : true,
            "path" : "date"
        }
    }
}'

