package actions

import (
	"users-es/utils"

	actuator "github.com/sinhashubham95/go-actuator"
)

var ENV, PORT = utils.Environment()

var Actuator = actuator.GetActuatorHandler(&actuator.Config{
	Endpoints: []int{
		actuator.Env,
		actuator.Info,
		actuator.Metrics,
		actuator.Ping,
		actuator.Shutdown,
		actuator.ThreadDump,
	},
	Env:     ENV,
	Name:    "users-es",
	Port:    80,
	Version: "0.0.1",
})
