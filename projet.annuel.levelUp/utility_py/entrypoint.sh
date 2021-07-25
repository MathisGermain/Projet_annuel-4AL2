#!/usr/bin/env bash
ulimit -s 128
timeout --signal=SIGTERM 10 python3 main.py
exit $?
