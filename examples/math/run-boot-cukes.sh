#!/bin/bash

#run all scenarios
boot cukes

#run a single tag
boot cukes -t @add

#run a single tag and output a json report
boot cukes -t @add -p json:report.json