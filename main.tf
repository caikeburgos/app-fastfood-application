provider "aws" {
  region = "us-east-1"
}

resource "aws_eks_cluster" "this" {
  name     = "fastfood-cluster-eks"
  role_arn = aws_iam_role.eks_cluster_role.arn
  

  vpc_config {
    subnet_ids = aws_subnet.this_subnets[*].id
  }
}

resource "aws_iam_role" "eks_cluster_role" {
  name = "fastfood-eks-role"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "eks.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
EOF
}

resource "aws_subnet" "this_subnets" {
  count = 2

  vpc_id                  = aws_vpc.this_vpc.id
  availability_zone       = element(["us-east-1a", "us-east-1b"], count.index)
  cidr_block              = element(["10.0.1.0/24", "10.0.2.0/24"], count.index)
  map_public_ip_on_launch = true
}

resource "aws_vpc" "this_vpc" {
  cidr_block = "10.0.0.0/16"
}

resource "aws_eks_fargate_profile" "this_fargate_profile" {
  cluster_name = aws_eks_cluster.this.name
  fargate_profile_name = "fastfood-fargate-profile"
  pod_execution_role_arn = aws_iam_role.fargate_execution_role.arn
  subnet_ids = aws_subnet.this_subnets[*].id

  selector {
    namespace = "default" # Namespace do Kubernetes
  }
}

resource "aws_iam_role" "fargate_execution_role" {
  name = "fastfood-eks-fargate-execution-role"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "eks-fargate-pods.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
EOF
}