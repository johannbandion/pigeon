export interface UserEntity {
  userName: string | undefined;
  password: string | undefined;
  chatEntities: ChatDto[] | undefined;
}

export interface UserDto {
  userName: string;
  password: string | undefined;
  chatEntities: ChatDto[] | undefined;
}

export interface ChatDto {
  chatId: number;
  userEntities: UserDto[] | undefined;
}

export interface MessageDto {
  messageId: number | undefined;
  chatEntity: ChatDto | undefined;
  userEntity: UserDto | undefined;
  messageTime: Date | undefined;
  messageText: string | undefined;
}

export interface Token {
  access_token: string;
}

export interface Contact {
  chatId: number;
  userName: string;
}
