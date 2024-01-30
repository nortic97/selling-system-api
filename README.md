# PosDash System Services

This documentation is basic to run and build the services for the correct functionality of the PosDash system.

## Run tests

To run the tests it is necessary to enter the test folder of each service and execute the following command.

```sh
go test -v
```

If your path is the root of the project run following command.

```sh
go test -v ./...
```

## Build Services

***To compile the code use build.sh***

1. Set permissions.
```sh
chmod +x ./build.sh
```
2. Execute.
```sh
./build.sh
```

The build generates a dist folder with the executables services.

## List of Services

### Entity
> - users-es
> - role-es
### Utility
> - auth-us
> - configPlatform-us
> - fileServer-us