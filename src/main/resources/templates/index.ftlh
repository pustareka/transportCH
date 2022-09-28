<html>
<head>
    <meta charset="UTF-8">
    <title>CH public transportation</title>
    <link rel="stylesheet" href="/css/styles.css" />
    <script type="text/javascript" src="/scripts/script.js"></script>
</head>
<body>
<div id="header">
    <h2>Public transportation Switzerland</h2>
</div>
<div id="content">
    <br/>
    <h3>Search public transport</h3>
    <form action="/connections" method="get" autocomplete="off">
        <div class="fromAndToWrapper">
            <fieldset>
                <div class="fromAndToStation">From station:
                    <input type="text" id="departureStation" name="from" required placeholder="From A" onKeyUp="getLocations(this.value, this.id)"/>
                    <div id="departureStationResult"></div>
                </div>
                <div class="fromAndToStation">To station:
                    <input type="text" id="arrivalStation" name="to" required placeholder="To B" onKeyUp="getLocations(this.value, this.id)"/>
                    <div id="arrivalStationResult"></div>
                </div>
            </fieldset>
        </div>
        <div class="dateAndTimeWrapper">
            <fieldset>
                <div class="dateAndTimeSelection">Select date:
                    <input type="date" name="departureDate"/>
                </div>
                <div class="dateAndTimeSelection">Select time:
                    <input type="time" name="departureTime"/>
                </div>
            </fieldset>
        </div>
        <input type="submit" value="Submit"/>
    </form>

    <#if model??>
        <h3>Results</h3>
        <table border="1">
            <tr>
                <td>Departure location name</td>
                <td>Departure time</td>
                <td>Departure platform</td>
                <td>Arrival location name</td>
                <td>Arrival time</td>
                <td>Arrival platform</td>
                <td>Travel duration</td>
            </tr>
            <#if model.connectionResults.connections??>
                <#list model.connectionResults.connections as connection>
                    <tr>
                        <td>${connection.from.station.name}</td>
                        <td>${connection.from.departure}</td>
                        <#if connection.from.platform??>
                            <td>${connection.from.platform}</td>
                        <#else>
                            <td>/</td>
                        </#if>
                        <td>${connection.to.station.name}</td>
                        <td>${connection.to.arrival}</td>
                        <#if connection.to.platform??>
                            <td>${connection.to.platform}</td>
                        <#else>
                            <td>/</td>
                        </#if>
                        <td>${connection.duration}</td>
                    </tr>
                </#list>
            </#if>
        </table>
    </#if>
    <br/><br/>
</div>
</body>
</html>


