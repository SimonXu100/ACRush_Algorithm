import logging
import boto3
from botocore.exceptions import ClientError


'''
Create a connection. Specify the region where you want to
setup ec2 along with your security credentials
'''
# connection
ec2_client = boto3.client('ec2')


# create key pair

response_key_pair = ec2_client.create_key_pair(KeyName='xss_boto3_ec2-keypair')

# capture the key and store it in a file
KeyPairOut = str(response_key_pair['KeyMaterial'])

outfile = open('/Users/xushusen/Desktop/Eduction_CU/Cloud_Computing/AWS_information/xss_boto3_ec2-keypair.pem', 'w')
outfile.write(KeyPairOut)

# create security group

response = ec2_client.describe_vpcs()
vpc_id = response.get('Vpcs', [{}])[0].get('VpcId', '')

try:
    response = ec2_client.create_security_group(GroupName='xss_boto3_SG_useast2',
                                         Description='boto_test',
                                         VpcId=vpc_id)
    security_group_id = response['GroupId']
    print('Security Group Created %s in vpc %s.' % (security_group_id, vpc_id))

    data = ec2_client.authorize_security_group_ingress(
        GroupId=security_group_id,
        IpPermissions=[
            {'IpProtocol': 'tcp',
             'FromPort': 80,
             'ToPort': 80,
             'IpRanges': [{'CidrIp': '0.0.0.0/0'}]},
            {'IpProtocol': 'tcp',
             'FromPort': 22,
             'ToPort': 22,
             'IpRanges': [{'CidrIp': '65.78.19.113/32'}]},
            {'IpProtocol': 'tcp',
             'FromPort': 443,
             'ToPort': 443,
             'IpRanges': [{'CidrIp': '0.0.0.0/0'}]}
        ])
    print('Ingress Successfully Set %s' % data)
except ClientError as e:
    print(e)


# launch you EC2 instance
# Assign these values before running the program
image_id = 'ami-02ccb28830b645a41'
instance_type = 't2.micro'
keypair_name = 'xss_boto3_ec2-keypair'

# Set up logging
logging.basicConfig(level=logging.DEBUG,
                    format='%(levelname)s: %(asctime)s: %(message)s')

ec2_client = boto3.client('ec2')
try:
    response = ec2_client.run_instances(ImageId=image_id,
                                        InstanceType=instance_type,
                                        KeyName=keypair_name,
                                        SecurityGroupIds=[
                                            security_group_id
                                        ],
                                        MinCount=1,
                                        MaxCount=1)
except ClientError as e:
    logging.error(e)


instance_info = response['Instances'][0]
instance_id = instance_info["InstanceId"]

# Provision and launch the EC2 instance
'''
if instance_info is not None:
    logging.info(f'Launched EC2 Instance {instance_info["InstanceId"]}')
    logging.info(f'    VPC ID: {instance_info["VpcId"]}')
    logging.info(f'    Private IP Address: {instance_info["PrivateIpAddress"]}')
    logging.info(f'    Current State: {instance_info["State"]["Name"]}')
'''


# reliably get the response object to find the IP address of the machine
# get external IP address
# which region instance was created
# instance ID







# stop instance
try:
    ec2_client.stop_instances(InstanceIds=[instance_id], DryRun=True)
except ClientError as e:
    if 'DryRunOperation' not in str(e):
        raise

# Dry run succeeded, call stop_instances without dryrun
try:
    response = ec2_client.stop_instances(InstanceIds=[instance_id], DryRun=False)
    print(response)
except ClientError as e:
    print(e)

# terminate instance

response = ec2_client.terminate_instances(InstanceIds=[instance_id])




# delete related key pairs  and security group
'''
response = ec2_client.delete_key_pair(KeyName='xss_boto3_ec2-keypair')
print(response)

try:
    response = ec2_client.delete_security_group(GroupId=security_group_id)
    print('Security Group Deleted')
except ClientError as e:
    print(e)
'''









