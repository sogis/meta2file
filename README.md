# metabean2file

## PySTAC
```
./mvnw -f lib/pom.xml compile
```

## Geometrien
```
SELECT 
    ST_ReducePrecision(ST_Transform(ST_ReducePrecision(geometrie, 1), 4326), 0.0001)
FROM 
    agi_hoheitsgrenzen_pub.hoheitsgrenzen_kantonsgrenze_generalisiert
;
```
