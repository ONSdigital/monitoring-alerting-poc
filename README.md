# monitoring-alerting-poc

## Pre-requisites

* Docker
* Change scrape_configs.static_configs.targets ip with your private machine ip in prometheus.yml file
* Install promtail binary on your machine and follow the link [https://grafana.com/docs/loki/latest/clients/promtail/installation/](https://grafana.com/docs/loki/latest/clients/promtail/installation/) 

Example of config.file
—————————————————

```
server:
http_listen_port: 9080
grpc_listen_port: 0
positions:
filename: /tmp/positions.yaml

clients:
- url: http://127.0.0.1:3100/loki/api/v1/push

scrape_configs:

- job_name: my-log-shipper
static_configs:
- targets:
- localhost
labels:
job: my-logging-monitoring-example
name: my-logging-monitoring
app: my-logging
service: my-logging-svc
agent: promtail
__path__: /opts/logs/my-file.log
```
—————————————————————

## Running

1. Configure *alertmanager.yml* with your Gmail address and app
password (if you want alerts to be sent to your email address)
2. Run `docker-compose up`

## Using

* Prometheus is available at [http://localhost:9090](http://localhost:9090)
* AlertManager is available at [http://localhost:9093](http://localhost:9093)
* Grafana is available at [http://localhost:3000](http://localhost:3000) (login is admin/admin)
* Application metrics are available at [http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus)
* Application endpoint is available at [http://localhost:8080/hello](http://localhost:8080/hello)
* Application endpoint generates 400 HTTP response is available at [http://localhost:8080/hello400](http://localhost:8080/hello400)
* Application endpoint generates 500 HTTP response is available at [http://localhost:8080/hello500](http://localhost:8080/hello500)
* Application latency endpoint is available at [http://localhost:8080/latency](http://localhost:8080/latency)
* Try importing the JVM (Micrometer) [dashboard](https://grafana.com/grafana/dashboards/6756) with ID 6756

