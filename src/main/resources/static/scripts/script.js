function debounce(cb, delay = 250) {
    let timeout

    return (...args) => {
        clearTimeout(timeout)
        timeout = setTimeout(() => {
            cb(...args)
        }, delay)
    }
}


const fetchLocations = debounce(function getLocations(val, inputId) {
    res = document.getElementById(inputId + "Result");
    res.innerHTML = '';
    if (val < 2) {
        return;
    }
    let list = '';
    fetch('/api/v1/publicTransportation/locations?query=' + val).then(
        function (response) {
            return response.json();
        }).then(function (data) {
        for (i=0; i<data.stations.length; i++) {
            list += '<li class="station-item">' + data.stations[i].name + '</li>';
        }
        res.innerHTML = '<ul>' + list + '</ul>';
        let stations = document.getElementsByClassName("station-item");
        for(var i=0; i < stations.length; i++) {
            var station = stations.item(i);
            station.addEventListener("click", function (e) {
                document.getElementById(inputId).value = e.target.textContent;
                res.innerHTML = "";
            });
        }
        return true;
    }).catch(function (err) {
        console.warn('Something went wrong.', err);
        return false;
    });
});

const getLocations = debounce(function(val, inputId){
    fetchLocations(val, inputId);
}, 500);