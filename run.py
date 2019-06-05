import argparse
import os
import subprocess as terminal
import xml.etree.ElementTree as xmlParser

ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

parser = argparse.ArgumentParser(description="Run Nutrition API tests",
                                 formatter_class=argparse.ArgumentDefaultsHelpFormatter)

parser.add_argument("--testName",
                    default="API test run")
parser.add_argument("-c", "--className", nargs='+',
                    help="Tests class name")
parser.add_argument("-g", "--groups", type=str, nargs='+',
                    help="Group to run the specified tests")
parser.add_argument("--environment", type=str,
                    help="Environment properties file", default='qa')
parser.add_argument("--generate", type=str,  default='True')


args = parser.parse_args()
url = ""

parameters = {"env": args.environment}

root = xmlParser.Element('suite', name=args.testName)


def generate_testngxml_file():
    file = open(ROOT_DIR + "/src/test/resources/xmlFiles/test_suit.xml", "w")
    file.write('<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >\n' + str(xmlParser.tostring(root)))
    file.close()


def generate_parameters():
    for name, value in parameters.items():
        parameter = xmlParser.Element('parameter', name='api.' + name, value=value)
        root.append(parameter)


def generate_test():
    test = xmlParser.Element('test', name=generate_test_name())
    if create_groups() != None:
        test.append(create_groups())
    test.append(get_classes())
    root.append(test)


def get_classes():
    if args.className != None:
        classes = xmlParser.Element('classes')
        for class_name in args.className:
            class_xmp = xmlParser.Element('class', name=class_name)
            classes.append(class_xmp)
        return classes
    else:
        packages = xmlParser.Element('packages')
        package = xmlParser.Element('package', name='tests.*')
        packages.append(package)
        return packages


def create_groups():
    global generate_paths
    if args.groups != None:
        groups_section = xmlParser.Element('groups')
        run = xmlParser.Element('run')

        for group in args.groups:

            if group.startswith("~"):
                group_test = xmlParser.Element('exclude', name=group[1:])
            else:
                group_test = xmlParser.Element('include', name=group)
            run.append(group_test)
        groups_section.append(run)
        return groups_section


def generate_test_name():
    name = 'Test run '
    test_classes = ''
    if args.className != None:
        for c in args.className:
            test_classes += str(c) + ','
            print test_classes
        if test_classes != '':
            name += 'for ' + test_classes[0:len(test_classes) - 1] + ' classes'
    if args.groups != None:
        test_groups = ''
        name += ' and for '
        for g in args.groups:
            test_groups += str(g) + ','
        if test_groups != '':
            name += '' + test_groups[0:len(test_groups) - 1] + ' groups'
    print name
    return name




generate_parameters()
generate_test()
generate_testngxml_file()


print ('mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/xmlFiles/test_suit.xml')
terminal.call(["mvn", "clean", "test", "-Dsurefire.suiteXmlFiles=src/test/resources/xmlFiles/test_suit.xml"])
terminal.call(["mvn", "io.qameta.allure:allure-maven:report"])