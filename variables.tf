variable "mysql_url" {
  type = string
}

variable "mysql_username" {
  sensitive = true
  type      = string
}

variable "mysql_password" {
  sensitive = true
  type      = string
}

variable "mercado_pago_api_key" {
  sensitive = true
  type      = string
}

variable "mercado_pago_id_loja" {
  sensitive = true
  type      = string
}

variable "mercado_pago_id_conta" {
  sensitive = true
  type      = number
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
