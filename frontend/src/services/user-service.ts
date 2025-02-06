import { User } from "@/types/user";

const baseUrl = `${process.env.NEXT_PUBLIC_BASE_API_URL}/api/user`;
console.log(baseUrl);

interface JwtRes {
  jwtToken: string
}

export async function getCustomToken(): Promise<JwtRes> {
  const res = await fetch("https://h4-10-fintech.onrender.com/");
  const jwtRes = res.json();
  return jwtRes;
}

export async function getUserByEmail(email: string): Promise<User> {
  const res = await fetch(`${baseUrl}/e/${email}`);
  const data = await res.json();
  return data;
}

export async function getAccessToken(): Promise<string> {
  const url = new URL("https://dev-byesylnv0qhe4lwt.us.auth0.com/oauth/token")
  const query = new URLSearchParams({
    grant_type: "client_credentials",
    client_id: "DqPYFyK6aQBIMBiJVf8NziAYq4rqipWJ",
    client_secret:
      "wJtFU4AU7s00rG6MHuY4-FxYjfVcu1FrjcBIPqkVwA10qr81hlvmm6m2IW3QBR-o",
    audience: "https://iupi.com",
  });
  url.search = query.toString();
  const headers = new Headers({
    "Content-Type": "application/x-www-form-urlencoded",
  });
  const req: RequestInit = {
    method: "POST",
    headers: headers,
  };
  const res = await fetch(url.href, req);
  const data = await res.json();
  return data.access_token;
}
