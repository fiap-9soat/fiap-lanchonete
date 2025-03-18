variable "mysql_url" {
  description = "URL de conexão com o banco de dados MySQL"
  type        = string
}

variable "mercado_pago_id_conta" {
  description = "ID da conta do Mercado Pago"
  type        = string
}

variable "aws_region" {
  type        = string
  description = "The region in which the resources will be created"
  default     = "us-east-1"
}

variable "aws_access_key" {
  type        = string
  description = "The aws development account access key"
}

variable "aws_secret_key" {
  type        = string
  description = "The aws development account secret key"
}

variable "aws_token_key" {
  type        = string
  description = "The aws development account token (optional)"
  default     = ""
}
