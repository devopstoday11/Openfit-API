#!/usr/bin/python

import argparse
import subprocess
import datetime
import os
from string import Template

parser = argparse.ArgumentParser(description="Execute REST API tests.",
                                 formatter_class=argparse.ArgumentDefaultsHelpFormatter)

parser.add_argument("-cp", "--classpath",
                    default="iron-man-1.0-SNAPSHOT-jar-with-dependencies.jar",
                    help="JAR file(s) with IronMan glue code and all external dependencies (e.g. Cucumber)")
parser.add_argument("-t", "--tests",
                    default="tests",
                    help="Path to the directory or file with the tests that should be executed")
parser.add_argument("--tags", type=str, nargs='+',
                    help="Cucumber tags to run the specified tests")
parser.add_argument("--logLevel",
                    help="Define log level(e.g. INFO, ERROR, WARN, DEBUG)")
parser.add_argument("environment",
                    help="Environment properties file")
parser.add_argument("-cc", "--ccData", type=int,
                    default=-1,
                    help="Set credit card data for users ")
parser.add_argument("-cid", "--coachId", type=int,
                    default=-1,
                    help="Set coach id for users ")

args = parser.parse_args()

args.__dict__['results'] = "results/" + datetime.datetime.now().strftime("%Y-%m-%d_%H-%M-%S")

if os.environ.get('JOB_URL') is not None:
    args.__dict__['JOB_URL'] = os.environ['JOB_URL']
else:
    args.__dict__['JOB_URL'] = ''

createResultDir = "mkdir -p " + args.__dict__['results'] + "/logs"
print createResultDir
subprocess.call(createResultDir, shell=True)

commandArgs = """java -cp $classpath \\
        -Djenkins.job.url=$JOB_URL \\
        -Diron-man.environment=$environment \\
        -Diron-man.results=$results \\
        -Diron-man.ccData=$ccData \\
        -Diron-man.coachId=$coachId \\"""

if None!=args.logLevel:
    commandArgs += """\n        -DlogLevel=$logLevel \\"""
commandArgs = commandArgs + """\n        cucumber.api.cli.Main \\
        --glue com.beachbody.qe.ironman \\
        $tests \\
        --plugin junit:$results/results.xml \\
        --plugin json:$results/results.json \\
        --plugin html:$results/html"""
if None!=args.tags:
    for tag in args.tags:
        commandArgs = commandArgs + """ \\ --tags """ + tag
commandTemplate = Template(commandArgs)
command = commandTemplate.substitute(args.__dict__)
print command
subprocess.call(command, shell=True)

deleteCurrentCucumberHtmlReports = """rm -rf """ + """../cucumber-html-reports"""
print deleteCurrentCucumberHtmlReports
subprocess.call(deleteCurrentCucumberHtmlReports, shell=True)

copyJsonCommand = """cp """ + args.__dict__['results'] + """/results.json results/latest.json"""
print copyJsonCommand
subprocess.call(copyJsonCommand, shell=True)
if (os.path.isfile(args.__dict__['results'] + "/club_users_info.csv")):
    copyJsonCommand = """cp """ + args.__dict__['results'] + """/club_users_info.csv results/club_users_info.csv"""
    print copyJsonCommand
subprocess.call(copyJsonCommand, shell=True)
